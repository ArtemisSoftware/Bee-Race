package com.artemissoftware.beerace.core.presentation.util.events

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class UiEventViewModel : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    protected suspend fun sendUiEvent(uiEvent: UiEvent) {
        _uiEvent.send(uiEvent)
    }
}