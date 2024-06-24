package com.meowsoft.opensos.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SmsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var ringtoneAction: RingtoneAction

    @Inject
    lateinit var flashlightAction: FlashlightAction

    @Inject
    lateinit var vibrateAction: VibrateAction

    override fun onReceive(context: Context, intent: Intent) = goAsync {
        if(intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return@goAsync
        }

        Log.d("TEST", "Broadcast received: ${intent.action}")
       // ringTonePlayer.perform()
        flashlightAction.perform()

    }.also {
        vibrateAction.perform()
    }

}

fun BroadcastReceiver.goAsync(
    context: CoroutineContext = Dispatchers.IO,
    block: suspend CoroutineScope.() -> Unit
) {
    val pendingResult = goAsync()
    @OptIn(DelicateCoroutinesApi::class)
    GlobalScope.launch(context) {
        try {
            block()
        } finally {
            pendingResult.finish()
        }
    }
}