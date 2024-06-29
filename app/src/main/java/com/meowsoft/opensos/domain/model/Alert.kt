package com.meowsoft.opensos.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    val phoneNumber: String = "",
    val textMessage: String = "",
    val actionTypes: List<AlertActionType>,
    val durationSeconds: Int = 10
)
