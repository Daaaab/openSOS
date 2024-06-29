package com.meowsoft.opensos.ui.addalert

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.meowsoft.opensos.common.NavigationConfig

object AddAlertNavigationConfig : NavigationConfig<AddAlertViewModel> {
    override val route: String = "alertsList/add"

    @Composable
    override fun getViewModel(): AddAlertViewModel = hiltViewModel()

    @Composable
    override fun Content() {
        AddAlertScreen(getViewModel())
    }
}