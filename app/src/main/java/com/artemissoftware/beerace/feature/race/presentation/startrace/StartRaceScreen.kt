package com.artemissoftware.beerace.feature.race.presentation.startrace

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.beerace.R
import com.artemissoftware.beerace.core.designsystem.composables.button.BRButton
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme

@Composable
fun StartRaceScreen(
    navigateToRace: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                BRButton(
                    text = stringResource(R.string.start_bee_race),
                    onClick = navigateToRace
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun StartRaceScreenPreview() {
    BeeRaceTheme {
        StartRaceScreen(navigateToRace = {})
    }
}