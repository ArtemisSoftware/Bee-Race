package com.artemissoftware.beerace.core.data.remote.dto


import com.squareup.moshi.Json

data class ErrorDto(
    @field:Json(name = "error")
    val error: ErrorDetailDto = ErrorDetailDto()
)