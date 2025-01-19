package com.artemissoftware.beerace.feature.race.presentation.tournament

import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.models.Racer
import com.artemissoftware.beerace.feature.race.presentation.tournament.model.RaceStatus

data class TournamentState(
    val status: RaceStatus = RaceStatus.NOT_STARTED,
    val isLoading: Boolean = false,
    val updateDelay: Long = 0L,
    val duration: RaceDuration = RaceDuration(),
    val racers: List<Racer> = emptyList()
)