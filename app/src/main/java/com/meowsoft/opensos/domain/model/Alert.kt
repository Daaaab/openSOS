package com.meowsoft.opensos.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Alert(
    val phoneNumber: String = "",
    val textMessage: String = "",
    val isRingtoneActionOn: Boolean = false,
    val isFlashlightActionOn: Boolean = false,
    val durationSeconds: Int = 10,
    val volume: Int = 0
)
