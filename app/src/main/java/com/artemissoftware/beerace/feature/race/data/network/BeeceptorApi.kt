package com.artemissoftware.beerace.feature.race.data.network

import com.artemissoftware.beerace.feature.race.data.network.dto.RaceStatusDto
import com.artemissoftware.beerace.feature.race.data.network.dto.DurationDto
import retrofit2.http.GET

interface BeeceptorApi {

    @GET("bees/race/duration")
    suspend fun getDuration(): DurationDto

    @GET("bees/race/status")
    suspend fun getRaceStatus(): RaceStatusDto

    companion object{
        const val BASE_URL = "https://rtest.proxy.beeceptor.com/"
    }
}