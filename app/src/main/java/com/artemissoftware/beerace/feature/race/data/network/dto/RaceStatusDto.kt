package com.artemissoftware.beerace.feature.race.data.network.dto


import com.squareup.moshi.Json

data class RaceStatusDto(
    @field:Json(name = "beeList")
    val bees: List<BeeDto> = emptyList()
)