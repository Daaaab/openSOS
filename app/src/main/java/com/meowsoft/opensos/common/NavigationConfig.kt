package com.meowsoft.opensos.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

interface NavigationConfig<T> {
    val route: String

    @Composable
    fun getViewModel(): T

    @Composable
    fun Content()
}