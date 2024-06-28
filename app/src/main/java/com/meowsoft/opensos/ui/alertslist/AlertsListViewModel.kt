package com.meowsoft.opensos.ui.alertslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meowsoft.opensos.common.NavigatorViewModel
import com.meowsoft.opensos.data.AlertsRepository
import com.meowsoft.opensos.domain.RingtoneAction
import com.meowsoft.opensos.domain.VibrateAction
import com.meowsoft.opensos.ui.addalert.AddAlertNavigationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertsListViewModel @Inject constructor(
    private val repository: AlertsRepository
) : NavigatorViewModel() {

    val alerts = repository
        .getAlerts()
        .map { it.toList() }

    init {
        Log.d("TEST", "AlertsListVM init")
    }

    fun navigateToAddAlert() {
        navigate(AddAlertNavigationConfig.route)
    }
}
