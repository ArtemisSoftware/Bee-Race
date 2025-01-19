package com.artemissoftware.beerace.core.presentation.util.events

sealed class UiEvent {
    data class Navigate(val value: Any) : UiEvent()
}