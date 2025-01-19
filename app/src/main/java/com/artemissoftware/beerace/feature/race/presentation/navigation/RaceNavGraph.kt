package com.artemissoftware.beerace.feature.race.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
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
fun NavGraphBuilder.raceNavGraph(navController: NavHostController) {

    composable<Route.StartRace> {
        StartRaceScreen(
            navigateToRace = { navController.navigate(Route.Race) },
        )
    }

    composable<Route.Race> {

        TournamentScreen(
            navigateToCaptcha = {
                navController.navigate(it)
            },
            navigateToWinner = {
                navController.navigate(it)
            },
            navigateToError = {
                navController.navigate(it)
            }
        )
    }

    composable<Route.Winner> {
        val route = it.toRoute<Route.Winner>()

        WinnerScreen(
            color = route.color,
            name = route.name,
            navigateToRace = { navController.navigate(Route.Race) },
        )
    }
}