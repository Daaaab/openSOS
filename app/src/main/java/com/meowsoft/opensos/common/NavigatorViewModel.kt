package com.meowsoft.opensos.common

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.SharedFlow

open class NavigatorViewModel : ViewModel() {

    lateinit var navHostController: NavHostController

    fun navigateUp() {
        navHostController.popBackStack()
    }

    fun navigate(route: String) {
        if(::navHostController.isInitialized) {
            navHostController.navigate(route)
        } else {
            Log.e("openSOS-error", "navHostController is not initialized")
        }
    }
}