package com.artemissoftware.beerace.core.domain.error

import com.artemissoftware.beerace.core.domain.models.Captcha

sealed interface DataError : Error {

    sealed class NetworkError : DataError {
        data class Error(val message: String) : NetworkError()
        data class CaptchaControl(val captcha: Captcha) : NetworkError()
        data object SocketTimeout : NetworkError()
        data object Unknown : NetworkError()
        data object UnknownHost : NetworkError()
        data object Connect : NetworkError()
    }
}