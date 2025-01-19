package com.artemissoftware.beerace.testdata

import com.artemissoftware.beerace.feature.race.data.network.dto.BeeDto
import com.artemissoftware.beerace.feature.race.data.network.dto.DurationDto
import com.artemissoftware.beerace.feature.race.data.network.dto.RaceStatusDto

object DtoTestData {

    val timeInSeconds = 120

    val beeDto1 = BeeDto(color = "Yellow", name = "Buzzy")
    val beeDto2 = BeeDto(color = "Black", name = "Shadow")

    val beesDto = listOf(beeDto1, beeDto2)

    val durationDto = DurationDto(timeInSeconds = timeInSeconds)

    val raceStatusDto = RaceStatusDto(bees = beesDto)
}