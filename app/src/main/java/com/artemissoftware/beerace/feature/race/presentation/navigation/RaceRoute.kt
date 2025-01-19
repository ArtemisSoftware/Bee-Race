package com.artemissoftware.beerace.feature.race.presentation.navigation

import kotlinx.serialization.Serializable

sealed class RaceRoute {
    @Serializable
    object StartRace

    @Serializable
    object Tournament

    @Serializable
    data class Winner(val color: String, val name: String)

    companion object {
        const val CAPTCHA = "captcha"
        const val ERROR = "error"
    }
}