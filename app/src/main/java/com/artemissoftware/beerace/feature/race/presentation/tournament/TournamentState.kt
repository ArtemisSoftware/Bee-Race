package com.artemissoftware.beerace.feature.race.presentation.tournament

import com.artemissoftware.beerace.feature.race.domain.models.RaceDuration
import com.artemissoftware.beerace.feature.race.domain.models.Racer

data class TournamentState(
    val isLoading: Boolean = false,
    val duration: RaceDuration = RaceDuration(),
    val racers: List<Racer> = emptyList()
)