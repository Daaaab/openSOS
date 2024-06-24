package com.meowsoft.opensos.ui.alertslist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.meowsoft.opensos.common.NavigationConfig
import com.meowsoft.opensos.common.NavigatorViewModel
import com.meowsoft.opensos.ui.addalert.AddAlertViewModel

object AlertListNavigationConfig : NavigationConfig {
    override val route: String = "alertsList/"

    @Composable
    override fun getViewModel(): NavigatorViewModel {
        val viewModel: AlertsListViewModel = hiltViewModel()
        return viewModel
    }

    @Composable
    override fun Content(navHostController: NavHostController) {
        AlertsListScreen(navHostController)
    }
}