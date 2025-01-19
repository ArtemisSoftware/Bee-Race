package com.artemissoftware.beerace.feature.race.domain.models

data class RaceDuration(val timeInSeconds: Int = 0){

    fun getDuration() = String.format("%02d:%02d", timeInSeconds / 60, timeInSeconds % 60)
}
