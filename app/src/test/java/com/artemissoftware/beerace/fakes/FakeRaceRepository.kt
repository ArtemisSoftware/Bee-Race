package com.artemissoftware.beerace.fakes

import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.feature.race.domain.models.RaceOverview
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.feature.race.domain.repository.RaceRepository
import com.artemissoftware.beerace.testdata.TestData.raceOverview
import com.artemissoftware.beerace.testdata.TestData.racers
import com.artemissoftware.beerace.testdata.TestData.resourceErrorCaptcha

class FakeRaceRepository: RaceRepository {

    var shouldReturnCaptchaError = false

    override suspend fun getRaceOverview(): Resource<RaceOverview> {
        return when{
            shouldReturnCaptchaError == true -> resourceErrorCaptcha
            else -> Resource.Success(raceOverview)
        }
    }

    override suspend fun getRacersPosition(): Resource<List<Racer>> {
        return Resource.Success(racers)
    }
}