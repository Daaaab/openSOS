package com.meowsoft.sosbutton

import android.app.Application
import com.meowsoft.sosbutton.common.di.allModules
import com.meowsoft.sosbutton.infrastructure.NotificationsHandler
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SosButtonApplication : Application() {

    private val notificationsHandler: NotificationsHandler by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SosButtonApplication)
            modules(allModules)
        }


        //notificationsHandler.createNotificationChannel()
        //notificationsHandler.showNotification()
    }
}