package com.artemissoftware.beerace.feature.race.data.network.source

import com.artemissoftware.beerace.core.data.remote.HandleApi
import com.artemissoftware.beerace.feature.race.data.network.BeeceptorApi
import com.artemissoftware.beerace.feature.race.data.network.dto.DurationDto
import com.artemissoftware.beerace.feature.race.data.network.dto.RaceStatusDto
import javax.inject.Inject

class BeeceptorApiSource @Inject constructor(
    private val beeceptorApi: BeeceptorApi
) {
    suspend fun getDuration(): DurationDto {
        return HandleApi.safeApiCall { beeceptorApi.getDuration() }
    }

    suspend fun getRaceStatus(): RaceStatusDto {
        return HandleApi.safeApiCall { beeceptorApi.getRaceStatus() }
    }
}