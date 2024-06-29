package com.meowsoft.opensos.ui.alertslist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.meowsoft.opensos.common.NavigatorViewModel
import com.meowsoft.opensos.data.AlertsRepository
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

//    init {
//        viewModelScope.launch {
//            repository.clearAlerts()
//        }
//    }

    fun onUiEvent(event: AlertListUiEvent) = when (event) {
        is AlertListUiEvent.DismissItemClicked -> dismissItem(event.index)
        is AlertListUiEvent.ItemClicked -> openItem(event.index)
    }

    fun navigateToAddAlert() {
        navigate(AddAlertNavigationConfig.route)
    }

    private fun dismissItem(index: Int) {
        viewModelScope.launch {
            repository.deleteAlert(index)
        }
    }

    private fun openItem(index: Int) {

    }
}
