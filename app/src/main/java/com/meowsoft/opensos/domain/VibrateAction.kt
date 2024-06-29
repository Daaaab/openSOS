package com.meowsoft.opensos.domain

import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import com.meowsoft.opensos.common.Settings
import javax.inject.Inject

class VibrateAction @Inject constructor(
    settings: Settings,
    private val vibratorManager: VibratorManager,
    private val vibrator: Vibrator
) {
    private val vibrationEffect: CombinedVibration

    init {
        vibrationEffect = CombinedVibration.createParallel(
            VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }

    fun perform() {

        val vibrationEffect1 =
            VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE)

        // it is safe to cancel other vibrations currently taking place
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)

    }
}