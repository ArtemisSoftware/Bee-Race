package com.artemissoftware.beerace.core.presentation.util.extension

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun String.toColor() : Color{
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: IllegalArgumentException) {
        MaterialTheme.colorScheme.primary
    }
}
