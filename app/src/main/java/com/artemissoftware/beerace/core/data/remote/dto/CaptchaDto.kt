package com.artemissoftware.beerace.core.data.remote.dto


import com.squareup.moshi.Json

data class CaptchaDto(
    @field:Json(name = "captchaUrl")
    val captchaUrl: String = ""
)