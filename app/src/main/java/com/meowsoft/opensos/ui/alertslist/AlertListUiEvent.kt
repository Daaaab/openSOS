package com.meowsoft.opensos.ui.alertslist

sealed interface AlertListUiEvent {
    data class ItemClicked(
        val index: Int
    ) : AlertListUiEvent
    data class DismissItemClicked(
        val index: Int
    ) : AlertListUiEvent
}