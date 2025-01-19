package com.artemissoftware.beerace.feature.race.presentation.tournament.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.Black10
import com.artemissoftware.beerace.core.designsystem.dimension
import com.artemissoftware.beerace.core.designsystem.spacing

@Composable
fun RaceCountdownTimer(
    raceDuration: RaceDuration,
    modifier: Modifier = Modifier,
    height: Dp = MaterialTheme.dimension.timerBannerHeight,
) {
    Box(
        modifier = modifier
            .height(height)
            .background(color = Black10),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing1_5)
        ) {
            Text(
                text = stringResource(R.string.time_remaining),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
            )

            Text(
                text = raceDuration.getDuration(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RaceCountdownTimerPreview() {
    BeeRaceTheme {
        RaceCountdownTimer(
            raceDuration = RaceDuration(70),
            modifier = Modifier.fillMaxWidth()
        )
    }
}