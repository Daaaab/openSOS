package com.meowsoft.sosbutton.infrastructure

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.RemoteViews
import com.meowsoft.sosbutton.R

class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        appWidgetIds.forEach { appWidgetId ->

            val smsIntent = Intent(context, SmsBroadcastReceiver::class.java)
            smsIntent.action = SmsBroadcastReceiver.SEND_DISTRESS_SMS_ACTION
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
}