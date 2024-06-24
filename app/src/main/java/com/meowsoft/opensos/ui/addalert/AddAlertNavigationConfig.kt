package com.meowsoft.opensos.ui.addalert

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.meowsoft.opensos.common.NavigationConfig
import com.meowsoft.opensos.common.NavigatorViewModel

object AddAlertNavigationConfig : NavigationConfig {
    override val route: String = "alertsList/add"

    @Composable
    override fun getViewModel(): NavigatorViewModel  {
        val viewModel: AddAlertViewModel = hiltViewModel()
        return viewModel
    }

    @Composable
    override fun Content(navHostController: NavHostController) {
        AddAlertScreen(navHostController)
    }
}