@file:OptIn(ExperimentalFoundationApi::class)

package com.artemissoftware.beerace.feature.race.presentation.tournament

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.spacing
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.core.presentation.composables.events.ManageUIEvents
import com.artemissoftware.beerace.presentation.navigation.Route
import com.artemissoftware.beerace.feature.race.presentation.tournament.composables.RaceCard
import com.artemissoftware.beerace.feature.race.presentation.tournament.composables.RaceCountdownTimer

@Composable
fun TournamentScreen(
    viewModel: TournamentViewModel = hiltViewModel(),
    navigateToWinner: (Route.Winner) -> Unit,
    navigateToCaptcha: (Route.Captcha) -> Unit,
    navigateToError: (Route.Error) -> Unit,
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle

    LaunchedEffect(lifecycle.currentState) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            viewModel.onTriggerEvent(TournamentEvent.ResumeRace)
        }
    }

    TournamentScreenContent(
        state = viewModel.state.collectAsState().value
    )

    ManageUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigate = {
            when(it.value){
                is Route.Winner -> navigateToWinner(it.value)
                is Route.Captcha -> navigateToCaptcha(it.value)
                is Route.Error -> navigateToError(it.value)
                else -> Unit
            }
        }
    )
}

@Composable
private fun TournamentScreenContent(
    state: TournamentState
) {
    val listState = rememberLazyListState()

    LaunchedEffect(state.racers) {
//        delay(1000)
//        listState.animateScrollToItem(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing2)
        ) {
            RaceCountdownTimer(
                raceDuration = state.duration,
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing0_5)
            ) {
                itemsIndexed(state.racers, key = { _, item -> item.name }) { index, racer ->
                    RaceCard(
                        position = index + 1,
                        racer = racer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItem(fadeInSpec = null, fadeOutSpec = null, placementSpec = tween(700))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TournamentScreenContentPreview() {
    BeeRaceTheme {
        TournamentScreenContent(
            state = TournamentState(
                racers = listOf(
                    Racer(name = "Speed Racer", color = "#8d62a1"),
                    Racer(name = "Speed Demon", color = "#8d62a1")
                )
            )
        )
    }
}