package com.meowsoft.opensos.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

interface NavigationConfig {
    val route: String

    @Composable
    fun getViewModel(): NavigatorViewModel

    @Composable
    fun Content(navHostController: NavHostController)
}