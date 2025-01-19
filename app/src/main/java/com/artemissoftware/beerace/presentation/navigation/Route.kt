package com.artemissoftware.beerace.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data class Error(val message: String)
}