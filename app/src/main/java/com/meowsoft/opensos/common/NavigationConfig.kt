package com.meowsoft.opensos.common

import androidx.compose.runtime.Composable

interface NavigationConfig<T> {
    val route: String

    @Composable
    fun getViewModel(): T

    @Composable
    fun Content()
}