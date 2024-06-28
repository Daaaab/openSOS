package com.meowsoft.opensos.ui.addalert

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meowsoft.opensos.common.NavigatorViewModel
import com.meowsoft.opensos.data.Alert
import com.meowsoft.opensos.data.AlertActionType
import com.meowsoft.opensos.data.AlertsRepository
import com.meowsoft.opensos.domain.RingtoneAction
import com.meowsoft.opensos.domain.VibrateAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAlertViewModel @Inject constructor(
    private val repository: AlertsRepository
) : NavigatorViewModel() {

    private val _addAlertUiState: MutableStateFlow<AddAlertUiState> =
        MutableStateFlow(AddAlertUiState())
    val addAlertUiState: StateFlow<AddAlertUiState> = _addAlertUiState.asStateFlow()

    private fun confirmAlert() {
        with(_addAlertUiState.value) {
            val alert = Alert(
                phoneNumber = phoneNumber,
                textMessage = textMessage,
                actionType = listOf(AlertActionType.RINGTONE)
            )

            viewModelScope.launch {
                repository.saveAlert(alert)
                navigateUp()
            }
        }
    }

    fun onUiEvent(uiEvent: AddAlertUiEvent) = when (uiEvent) {
        is AddAlertUiEvent.ConfirmClicked -> confirmAlert()
        is AddAlertUiEvent.MessageInput -> onTextMessageInput(uiEvent.message)
        is AddAlertUiEvent.PhoneNumberInput -> onPhoneNumberInput(uiEvent.phoneNumber)
        is AddAlertUiEvent.ToggleFlashlightAction -> onToggleFlashlightAction(uiEvent.isOn)
        is AddAlertUiEvent.ToggleRingtoneAction -> onToggleRingtoneAction(uiEvent.isOn)
    }

    private fun onPhoneNumberInput(phoneNumber: String) {
        _addAlertUiState.value = _addAlertUiState.value.copy(phoneNumber = phoneNumber)
    }

    private fun onTextMessageInput(message: String) {
        _addAlertUiState.value = _addAlertUiState.value.copy(textMessage = message)
    }

    private fun onToggleRingtoneAction(isOn: Boolean) {
        _addAlertUiState.value = _addAlertUiState.value.copy(isRingtoneActionOn = isOn)
    }

    private fun onToggleFlashlightAction(isOn: Boolean) {
        _addAlertUiState.value = _addAlertUiState.value.copy(isFlashlightActionOn = isOn)
    }
}
