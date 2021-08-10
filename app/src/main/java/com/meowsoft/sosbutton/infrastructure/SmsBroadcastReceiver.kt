package com.meowsoft.sosbutton.infrastructure

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.RingtoneManager
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.meowsoft.sosbutton.common.Config
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import java.util.*
import java.util.concurrent.TimeUnit


class SmsBroadcastReceiver : BroadcastReceiver() {

    companion object{
        private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    }

    private fun setVolume(audioManager: AudioManager, volume: Int){
        audioManager
            .setStreamVolume(
                AudioManager.STREAM_RING,
                volume,
                AudioManager.FLAG_SHOW_UI + AudioManager.FLAG_PLAY_SOUND
            )
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == SMS_RECEIVED) {

            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)

            Log.d("TEST", "Message received")

            messages
                .map { smsMessage ->
                    smsMessage.messageBody
                }
                .forEach { smsBody ->
                    if(smsBody.contains(Config.SOS_MESSAGE)){
                        Log.d("TEST", "is SOS Message")
                        val ringtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                        val ringtone = RingtoneManager.getRingtone(context!!.applicationContext, ringtoneUri)

                        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

                        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)

                        val originalVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING)
                        val originalMode = audioManager.mode

                        Single
                            .just(ringtone)
                            .delay(Config.RING_TIME_SECONDS, TimeUnit.SECONDS)
                            .doOnSubscribe {
                                audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                                setVolume(audioManager, maxVolume)
                                Log.d("TEST", "Set Volume: $maxVolume and mode: ${audioManager.ringerMode}")
                                ringtone.play()
                            }
                            .subscribeBy {
                                ringtone.stop()
                                setVolume(audioManager, originalVolume)
                                audioManager.ringerMode = originalMode
                                Log.d("TEST", "Set Volume: $originalVolume and mode: ${audioManager.ringerMode}")
                            }

                    }
                }

        }
    }
}