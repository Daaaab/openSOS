package com.meowsoft.opensos.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log
import com.meowsoft.opensos.data.AlertsRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
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

    @Inject
    lateinit var alertsRepository: AlertsRepository

    override fun onReceive(context: Context, intent: Intent) = goAsync {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return@goAsync
        }

        Log.d("openSOS", "Broadcast received: ${intent.action}")
        alertsRepository
            .getAlerts()
            .single()
            .forEach { alert ->




                if(alert.isRingtoneActionOn) {
                    ringtoneAction.perform(alert.durationSeconds, alert.volume)
                }
                if(alert.isFlashlightActionOn) {
                    flashlightAction.perform(alert.durationSeconds)
                }
            }
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