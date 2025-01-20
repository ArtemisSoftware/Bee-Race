package com.artemissoftware.beerace.feature.race.domain.models

data class RaceOverview(
    val updateDelay: Long,
    val raceDuration: RaceDuration,
    val racers: List<Racer>
)
