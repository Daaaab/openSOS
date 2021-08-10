package com.meowsoft.sosbutton.infrastructure

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.widget.RemoteViews
import android.widget.Toast
import com.meowsoft.sosbutton.R
import com.meowsoft.sosbutton.common.Config

class WidgetProvider : AppWidgetProvider() {

    companion object{
        private const val SEND_SMS_ACTION = "Send_sms_action"
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->

            val smsIntent = Intent(context, WidgetProvider::class.java)
            smsIntent.action = SEND_SMS_ACTION
            smsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            val smsPendingIntent = PendingIntent.getBroadcast(context, 0, smsIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget
            ).apply {
                setOnClickPendingIntent(R.id.btnWidgetSos, smsPendingIntent)
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        intent?.let {
            when(it.action){
                SEND_SMS_ACTION -> {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(
                        Config.SOS_NUMBER,
                        null,
                        Config.SOS_MESSAGE,
                        null,
                        null
                    )

                    Toast.makeText(context, Config.SOS_MESSAGE_INFO, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}