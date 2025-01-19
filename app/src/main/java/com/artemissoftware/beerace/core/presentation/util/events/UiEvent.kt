package com.artemissoftware.beerace.core.presentation.util.events

sealed class UiEvent {
    data class NavigateWithRoute(val value: Any) : UiEvent()
    data class Navigate(val value: Any, val route: String = "") : UiEvent()
}