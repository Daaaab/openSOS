package com.meowsoft.opensos.ui.addalert

sealed interface AddAlertUiEvent {
    data class PhoneNumberInput(
        val phoneNumber: String
    ) : AddAlertUiEvent

    data class MessageInput(
        val message: String
    ) : AddAlertUiEvent

    data class ToggleRingtoneAction(
        val isOn: Boolean
    ) : AddAlertUiEvent

    data class ToggleFlashlightAction(
        val isOn: Boolean
    ) : AddAlertUiEvent
}