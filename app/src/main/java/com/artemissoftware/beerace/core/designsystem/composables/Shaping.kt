package com.artemissoftware.beerace.core.designsystem.composables

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shaping(
    val noCorners: Shape,
)

internal val shape = Shaping(
    noCorners = RoundedCornerShape(0),
)

internal val localShape = staticCompositionLocalOf<Shaping> { throw IllegalStateException("No theme installed") }

val MaterialTheme.shape: Shaping
    @Composable
    get() = localShape.current