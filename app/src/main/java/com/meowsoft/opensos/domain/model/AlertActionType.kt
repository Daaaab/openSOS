package com.meowsoft.opensos.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class AlertActionType {
    RINGTONE,
    FLASHLIGHT
}