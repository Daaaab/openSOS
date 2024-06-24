package com.meowsoft.opensos.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow

open class NavigatorViewModel : ViewModel() {

    private val navigationLiveData = MutableLiveData<String>()
    val navigationFlow = navigationLiveData.asFlow()

    fun navigate(route: String) {
        navigationLiveData.value = route
    }
}