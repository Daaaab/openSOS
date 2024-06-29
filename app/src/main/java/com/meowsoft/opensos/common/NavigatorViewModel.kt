package com.meowsoft.opensos.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

open class NavigatorViewModel : ViewModel() {

    lateinit var navHostController: NavHostController

    fun navigateUp() {
        navHostController.popBackStack()
    }

    fun navigate(route: String) {
        if (::navHostController.isInitialized) {
            navHostController.navigate(route)
        } else {
            Log.e("openSOS-error", "navHostController is not initialized")
        }
    }
}