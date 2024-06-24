package com.meowsoft.opensos.domain

import android.media.AudioManager
import com.meowsoft.opensos.common.Settings
import kotlinx.coroutines.delay
import javax.inject.Inject

class RingtoneAction @Inject constructor(
    settings: Settings,
    private val audioManager: AudioManager
) : Action {
    private val ringtone = settings.ringtone
    private val stream = settings.ringToneStream
    private val durationMills = settings.duration * 1000L

    override suspend fun perform() {
        val originalVolume = maxVolume()
        val originalMode = setRingModeToRing()

        ringtone.play()

        delay(durationMills)

        ringtone.stop()

        setRingMode(originalMode)
        setVolume(originalVolume)
    }

    private fun setVolume(volume: Int) = audioManager
        .setStreamVolume(stream, volume, 0)

    private fun maxVolume(): Int {
        val volumeLevel = audioManager.getStreamVolume(stream)
        val maxVolumeLevel = audioManager.getStreamMaxVolume(stream)

        setVolume(maxVolumeLevel)

        return volumeLevel
    }

    private fun setRingModeToRing(): Int {
        val originalMode = audioManager.ringerMode
        setRingMode(AudioManager.RINGER_MODE_NORMAL)
        return originalMode
    }

    private fun setRingMode(ringMode: Int) {
        audioManager.ringerMode = ringMode
    }
}