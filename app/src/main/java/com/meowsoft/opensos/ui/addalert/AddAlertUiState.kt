package com.meowsoft.opensos.ui.addalert

data class AddAlertUiState(
    val phoneNumber: String = "",
    val textMessage: String = "",
    val isRingtoneActionOn: Boolean = false,
    val isFlashlightActionOn: Boolean = false
)