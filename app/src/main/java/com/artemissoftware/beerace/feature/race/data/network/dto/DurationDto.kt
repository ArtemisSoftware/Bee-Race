package com.artemissoftware.beerace.feature.race.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class DurationDto(
    @field:Json(name = "timeInSeconds")
    val timeInSeconds: Int = 0
)