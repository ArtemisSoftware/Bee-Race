package com.artemissoftware.beerace.feature.recaptcha.presentation.navigation

import kotlinx.serialization.Serializable

object ReCaptchaRoute {

    @Serializable
    data class Captcha(val url: String)
}