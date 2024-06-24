package com.meowsoft.opensos.ui

import com.meowsoft.opensos.ui.addalert.AddAlertNavigationConfig
import com.meowsoft.opensos.ui.alertslist.AlertListNavigationConfig

object Destinations {
    val startDestination = AlertListNavigationConfig.route

    val list = listOf(
        AlertListNavigationConfig,
        AddAlertNavigationConfig
    )
}