package com.meowsoft.opensos.data

import kotlinx.serialization.Serializable

@Serializable
enum class AlertActionType {
    RINGTONE,
    FLASHLIGHT
}