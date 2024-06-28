package com.meowsoft.opensos.common

import android.content.Context
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import javax.inject.Inject

class Settings @Inject constructor(context: Context) {

    val duration = 10

    val ringtone: Ringtone
    val ringToneStream = AudioManager.STREAM_RING
    val volume: Float = 1F

    val flashDuration = 0.5F

    init {
        val ringtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        ringtone = RingtoneManager.getRingtone(context.applicationContext, ringtoneUri)
    }
}