package com.meowsoft.opensos.ui.alertslist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowsoft.opensos.common.NavigationConfig

object AlertListNavigationConfig : NavigationConfig<AlertsListViewModel> {
    override val route: String = "alertsList/"

    @Composable
    override fun getViewModel(): AlertsListViewModel = hiltViewModel()

    @Composable
    override fun Content() {
        AlertsListScreen(getViewModel())
    }
}
