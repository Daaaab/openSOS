package com.meowsoft.opensos.domain

import android.hardware.camera2.CameraManager
import com.meowsoft.opensos.common.Settings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class FlashlightAction @Inject constructor(
    settings: Settings,
    private val cameraManager: CameraManager
) : Action {

    private val timer: Flow<Int>

    init {
        val blinksCount = (settings.duration / settings.flashInterval).roundToInt()
        val blinkDuration = (settings.flashInterval * 1000).roundToLong()

        timer = (0..blinksCount)
            .asSequence()
            .asFlow()
            .onEach { delay(blinkDuration) }
    }

    override suspend fun perform() {
        var camId = ""

        try {
            camId = cameraManager.cameraIdList[0]
            var isOn = false

            timer.collect {
                isOn = !isOn
                enableFlashlight(cameraManager, camId, isOn)
            }

            enableFlashlight(cameraManager, camId, false)

        } catch (_: Exception) {
            enableFlashlight(cameraManager, camId, false)
        }
    }

    private fun enableFlashlight(cameraManager: CameraManager, camId: String, isOn: Boolean) {
        cameraManager.setTorchMode(camId, isOn)
    }
}