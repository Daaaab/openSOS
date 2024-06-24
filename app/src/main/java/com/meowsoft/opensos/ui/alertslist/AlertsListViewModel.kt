package com.meowsoft.opensos.ui.alertslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meowsoft.opensos.common.NavigatorViewModel
import com.meowsoft.opensos.domain.RingtoneAction
import com.meowsoft.opensos.domain.VibrateAction
import com.meowsoft.opensos.ui.addalert.AddAlertNavigationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertsListViewModel @Inject constructor() : NavigatorViewModel() {

    fun navigateToAddAlert() {
        navigate(AddAlertNavigationConfig.route)
    }
}
