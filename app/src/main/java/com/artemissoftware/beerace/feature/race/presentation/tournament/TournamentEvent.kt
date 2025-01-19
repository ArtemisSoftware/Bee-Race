package com.artemissoftware.beerace.feature.race.presentation.tournament

sealed interface TournamentEvent {
    data object ResumeRace: TournamentEvent
}