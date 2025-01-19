package com.artemissoftware.beerace.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.artemissoftware.beerace.core.presentation.screen.ErrorScreen
import com.artemissoftware.beerace.feature.race.presentation.navigation.RaceRoute
import com.artemissoftware.beerace.feature.race.presentation.navigation.raceNavGraph
import com.artemissoftware.beerace.feature.recaptcha.presentation.navigation.ReCaptchaRoute
import com.artemissoftware.beerace.feature.recaptcha.presentation.navigation.reCaptchaNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: Any = RaceRoute.StartRace,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        raceNavGraph(
            navController = navController,
            navigateToCaptcha = {
                navController.navigate(ReCaptchaRoute.Captcha(it))
            }
        )

        reCaptchaNavGraph(navController)

        composable<Route.Error> {
            val route = it.toRoute<Route.Error>()

            ErrorScreen (
                errorMessage = route.message,
                onRetry = { navController.popBackStack() },
            )
        }
    }
}