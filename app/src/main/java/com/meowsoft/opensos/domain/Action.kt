package com.meowsoft.opensos.domain

interface Action {
    suspend fun perform()
}
