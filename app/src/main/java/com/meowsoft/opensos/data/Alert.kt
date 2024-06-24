package com.meowsoft.opensos.data

data class Alert(
    val phoneNumber: String,
    val textMessage: String,
    val actions: List<AlertActionType>
)