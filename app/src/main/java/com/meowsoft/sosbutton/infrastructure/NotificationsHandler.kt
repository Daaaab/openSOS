package com.meowsoft.sosbutton.infrastructure

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.meowsoft.sosbutton.R

class NotificationsHandler(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "Distress_channel"
        private const val CHANNEL_NAME = "Distress channel"
        private const val CHANNEL_DESCRIPTION = "Distress_channel"

        private const val NOTIFICATION_ID = 111000111
    }

    private val notificationManager
        get() = NotificationManagerCompat.from(context)

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_DESCRIPTION
            val importance = NotificationManagerCompat.IMPORTANCE_HIGH

            val channel = with(
                NotificationChannelCompat.Builder(CHANNEL_ID, importance)
            ){
                setName(name)
                setDescription(descriptionText)
                build()
            }

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification() =
        with(
            notificationManager
        ){
            notify(
                NOTIFICATION_ID,
                getBuilder().build()
            )
        }

    private fun getPendingIntent(): PendingIntent {
        val smsIntent = Intent(context, SmsBroadcastReceiver::class.java)
            .apply {
                action = SmsBroadcastReceiver.SEND_DISTRESS_SMS_ACTION
            }

        val smsPendingIntent =
            PendingIntent
                .getBroadcast(
                    context,
                    1,
                    smsIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

        return smsPendingIntent
    }

    private fun getBuilder() =
        with(
            NotificationCompat.Builder(context, CHANNEL_ID)
        ){
            setSmallIcon(R.drawable.ic_stat_onesignal_default)
            setContentTitle("Wyślij SOS")
            setContentText("Wyślij SOS")
            setCategory(NotificationCompat.CATEGORY_ALARM)
            setOngoing(true)
            setContentIntent(getPendingIntent())
            setPriority(NotificationCompat.PRIORITY_HIGH)
        }
}