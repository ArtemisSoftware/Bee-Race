package com.artemissoftware.beerace.feature.race.presentation.winner

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.Black10
import com.artemissoftware.beerace.core.designsystem.Gray10
import com.artemissoftware.beerace.core.designsystem.composables.button.BRButton
import com.artemissoftware.beerace.core.designsystem.dimension
import com.artemissoftware.beerace.core.designsystem.spacing
import com.artemissoftware.beerace.core.presentation.util.extension.toColor
import com.artemissoftware.beerace.core.presentation.util.extension.toOrdinal
import com.artemissoftware.beerace.core.ui.composables.icon.RacerIcon
import com.artemissoftware.beerace.feature.race.presentation.tournament.TournamentEvent

@Composable
fun WinnerScreen(
    name: String,
    color: String,
    navigateToRace: () -> Unit,
    navigateToStart: () -> Unit,
) {

    BackHandler {
        navigateToStart()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.winner),
                    style = MaterialTheme.typography.displayLarge,
                    color = Black10,
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing2))

                RacerIcon(
                    size = MaterialTheme.dimension.iconSizeBig,
                    backgroundColor = color.toColor(),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing2))

                Text(
                    text = 1.toOrdinal().asString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Black10
                )

                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray10
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.spacing4))

                BRButton(
                    text = stringResource(R.string.re_start_bee_race),
                    onClick = navigateToRace
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private  fun WinnerScreenPreview() {
    BeeRaceTheme {
        WinnerScreen(
            name = "Michael Valiant",
            color = "#8d62a1",
            navigateToRace = {},
            navigateToStart = {}
        )
    }
}