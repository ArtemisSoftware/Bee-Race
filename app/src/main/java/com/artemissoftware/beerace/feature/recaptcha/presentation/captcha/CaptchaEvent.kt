package com.artemissoftware.beerace.feature.recaptcha.presentation.captcha

sealed interface CaptchaEvent {
    data object CaptchaSolved: CaptchaEvent
}