package com.artemissoftware.beerace.core.presentation.composables.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.artemissoftware.beerace.core.presentation.util.events.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ManageUIEvents(
    uiEvent: Flow<UiEvent>,
    onNavigate: (UiEvent.Navigate) -> Unit = {},
) {
    LaunchedEffect(key1 = Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> { onNavigate(event) }
            }
        }
    }
}