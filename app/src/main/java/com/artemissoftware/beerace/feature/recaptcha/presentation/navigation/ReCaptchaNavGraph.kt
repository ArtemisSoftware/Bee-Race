package com.artemissoftware.beerace.feature.recaptcha.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.artemissoftware.beerace.feature.race.presentation.navigation.RaceRoute
import com.artemissoftware.beerace.feature.recaptcha.presentation.captcha.CaptchaScreen

fun NavGraphBuilder.reCaptchaNavGraph(navController: NavHostController) {

    composable<ReCaptchaRoute.Captcha> {
        val route = it.toRoute<ReCaptchaRoute.Captcha>()

        CaptchaScreen(
            url = route.url,
            onAbandonCaptcha = { navController.popBackStack(RaceRoute.StartRace, inclusive = false) },
            onCaptchaSolved = { navController.popBackStack() },
        )
    }
}