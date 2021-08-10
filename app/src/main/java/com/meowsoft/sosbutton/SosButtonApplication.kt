package com.meowsoft.sosbutton

import android.app.Application
import com.meowsoft.sosbutton.common.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SosButtonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SosButtonApplication)
            modules(allModules)
        }
    }
}