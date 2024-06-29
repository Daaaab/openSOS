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
    private val settings: Settings,
    private val cameraManager: CameraManager
) {
    suspend fun perform(duration: Int) {
        var camId = ""

        try {
            camId = cameraManager.cameraIdList[0]
            var isOn = false

            getTimer(duration).collect {
                isOn = !isOn
                enableFlashlight(cameraManager, camId, isOn)
            }

            enableFlashlight(cameraManager, camId, false)

        } catch (_: Exception) {
            enableFlashlight(cameraManager, camId, false)
        }
    }

    private fun getTimer(duration: Int): Flow<Int> {
        val blinkDuration = (settings.flashInterval * 1000).roundToLong()
        val blinksCount = (duration / settings.flashInterval).roundToInt()

        return (0..blinksCount)
            .asSequence()
            .asFlow()
            .onEach { delay(blinkDuration) }
    }

    private fun enableFlashlight(cameraManager: CameraManager, camId: String, isOn: Boolean) {
        cameraManager.setTorchMode(camId, isOn)
    }
}