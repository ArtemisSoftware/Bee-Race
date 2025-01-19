package com.artemissoftware.beerace.feature.race.presentation.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.artemissoftware.beerace.feature.race.presentation.startrace.StartRaceScreen
import com.artemissoftware.beerace.feature.race.presentation.tournament.TournamentScreen
import com.artemissoftware.beerace.feature.race.presentation.winner.WinnerScreen
import com.artemissoftware.beerace.presentation.navigation.Route

const val RACE_GRAPH = "race_graph"

@SuppressLint("ComposableDestinationInComposeScope")
fun NavGraphBuilder.raceNavGraph(
    navController: NavHostController,
    navigateToCaptcha: (String) -> Unit
) {

    composable<RaceRoute.StartRace> {
        StartRaceScreen(
            navigateToRace = { navController.navigate(RaceRoute.Tournament) },
        )
    }

    composable<RaceRoute.Tournament> {

        TournamentScreen(
            navigateToCaptcha = { url -> navigateToCaptcha(url) },
            navigateToWinner = {
                navController.navigate(it)
            },
            navigateToError = { message ->
                navController.navigate(Route.Error(message))
            },
            navigateBack = { navController.popBackStack() }
        )
    }

    composable<RaceRoute.Winner> {
        val route = it.toRoute<RaceRoute.Winner>()

        WinnerScreen(
            color = route.color,
            name = route.name,
            navigateToRace = { navController.navigate(RaceRoute.Tournament) },
            navigateToStart = {
                navController.popBackStack(RaceRoute.StartRace, inclusive = false)
            }
        )
    }
}