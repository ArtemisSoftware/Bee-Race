package com.artemissoftware.beerace.fakes

import com.artemissoftware.beerace.feature.race.data.network.BeeceptorApi
import com.artemissoftware.beerace.feature.race.data.network.dto.DurationDto
import com.artemissoftware.beerace.feature.race.data.network.dto.RaceStatusDto
import com.artemissoftware.beerace.testdata.DtoTestData

class FakeBeeceptorApi : BeeceptorApi {

    var shouldThrowError = false

    override suspend fun getDuration(): DurationDto {
        if (shouldThrowError) throw Exception("Network error while fetching duration")
        return DtoTestData.durationDto
    }

    override suspend fun getRaceStatus(): RaceStatusDto {
        if (shouldThrowError) throw Exception("Network error while fetching race status")
        return RaceStatusDto(
            bees = DtoTestData.beesDto
        )
    }
}