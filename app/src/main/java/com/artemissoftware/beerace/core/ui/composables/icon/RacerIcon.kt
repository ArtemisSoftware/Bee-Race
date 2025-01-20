package com.artemissoftware.beerace.core.ui.composables.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.dimension

@Composable
fun RacerIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int = R.drawable.ic_bee,
    size: DpSize = MaterialTheme.dimension.iconSizeMedium,
    backgroundColor: Color = Color.Blue
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(MaterialTheme.dimension.iconSizeSmall),
            painter = painterResource(id = icon),
            contentDescription = "Racer Icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RacerIconPreview() {
    BeeRaceTheme {
        RacerIcon()
    }
}