package com.artemissoftware.beerace.feature.race.data.mapper

import com.artemissoftware.beerace.feature.race.data.network.dto.BeeDto
import com.artemissoftware.beerace.core.data.remote.dto.CaptchaDto
import com.artemissoftware.beerace.feature.race.data.network.dto.DurationDto
import com.artemissoftware.beerace.domain.models.Captcha
import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.models.Racer

fun DurationDto.toDuration(): RaceDuration {
    return RaceDuration(timeInSeconds = timeInSeconds)
}

fun BeeDto.toRacer(): Racer {
    return Racer(
        name = name,
        color = color
    )
}

fun CaptchaDto.toCaptcha(): Captcha {
    return Captcha(url = captchaUrl)
}