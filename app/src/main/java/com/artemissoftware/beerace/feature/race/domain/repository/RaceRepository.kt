package com.artemissoftware.beerace.feature.race.domain.repository

import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.feature.race.domain.models.RaceOverview
import com.artemissoftware.beerace.feature.race.domain.models.Racer

interface RaceRepository {
    suspend fun getRaceOverview(): Resource<RaceOverview>
    suspend fun getRacersPosition(): Resource<List<Racer>>
}