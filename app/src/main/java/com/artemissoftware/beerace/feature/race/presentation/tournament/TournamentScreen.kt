@file:OptIn(ExperimentalFoundationApi::class)

package com.artemissoftware.beerace.feature.race.presentation.tournament

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.artemissoftware.beerace.core.designsystem.BeeRaceTheme
import com.artemissoftware.beerace.core.designsystem.spacing
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.core.presentation.composables.events.ManageUIEvents
import com.artemissoftware.beerace.core.presentation.util.UiText
import com.artemissoftware.beerace.feature.race.presentation.navigation.RaceRoute
import com.artemissoftware.beerace.presentation.navigation.Route
import com.artemissoftware.beerace.feature.race.presentation.tournament.composables.RaceCard
import com.artemissoftware.beerace.feature.race.presentation.tournament.composables.RaceCountdownTimer
import kotlinx.coroutines.delay

@Composable
fun TournamentScreen(
    viewModel: TournamentViewModel = hiltViewModel(),
    navigateToWinner: (RaceRoute.Winner) -> Unit,
    navigateToCaptcha: (String) -> Unit,
    navigateToError: (String) -> Unit,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle

    LaunchedEffect(lifecycle.currentState) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            viewModel.onTriggerEvent(TournamentEvent.ResumeRace)
        }
    }

    BackHandler {
        viewModel.onTriggerEvent(TournamentEvent.CancelRace)
        navigateBack()
    }

    TournamentScreenContent(
        state = viewModel.state.collectAsState().value
    )

    ManageUIEvents(
        uiEvent = viewModel.uiEvent,
        onNavigateWithRoute = {
            when(it.value){
                is RaceRoute.Winner -> navigateToWinner(it.value)
                else -> Unit
            }
        },
        onNavigate = {
            when(it.route){
                RaceRoute.CAPTCHA -> navigateToCaptcha(it.value as String)
                RaceRoute.ERROR -> navigateToError((it.value as UiText).asString(context))
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
        delay(1500)
        listState.animateScrollToItem(0)
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

            if(state.isLoading){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            else {
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