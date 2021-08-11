package com.meowsoft.sosbutton.common.di

import com.meowsoft.sosbutton.infrastructure.NotificationsHandler
import org.koin.dsl.module

val appModule = module{

    single<NotificationsHandler> {
        NotificationsHandler(get())
    }
}