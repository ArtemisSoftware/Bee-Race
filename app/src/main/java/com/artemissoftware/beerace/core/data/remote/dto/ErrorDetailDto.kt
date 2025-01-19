package com.artemissoftware.beerace.core.data.remote.dto


import com.squareup.moshi.Json

data class ErrorDetailDto(
    @field:Json(name = "code")
    val code: Int = 0,
    @field:Json(name = "message")
    val message: String = ""
)