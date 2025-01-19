package com.artemissoftware.beerace.feature.race.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class BeeDto(
    @field:Json(name = "color")
    val color: String = "",
    @field:Json(name = "name")
    val name: String = ""
)