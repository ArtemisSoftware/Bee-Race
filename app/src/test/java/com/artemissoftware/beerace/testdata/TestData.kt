package com.artemissoftware.beerace.testdata

import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.core.domain.error.DataError
import com.artemissoftware.beerace.core.domain.models.Captcha
import com.artemissoftware.beerace.feature.race.data.network.dto.BeeDto
import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.models.RaceOverview
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.testdata.DtoTestData.timeInSeconds

object TestData {

    val raceDuration = RaceDuration(timeInSeconds = timeInSeconds)
    val updateDelay = 4000L

    val racer1 = Racer(color = "Yellow", name = "Buzzy")
    val racer2 = Racer(color = "Black", name = "Shadow")

    val racers = listOf(racer1, racer2)

    val raceOverview = RaceOverview(
        raceDuration = raceDuration,
        racers = racers,
        updateDelay = updateDelay
    )

    private val errorCaptcha = DataError.NetworkError.CaptchaControl(Captcha("https://captcha.url"))
    val resourceErrorCaptcha = Resource.Failure<RaceOverview>(errorCaptcha)
}