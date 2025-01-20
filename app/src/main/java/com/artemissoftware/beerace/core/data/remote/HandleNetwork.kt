package com.artemissoftware.beerace.core.data.remote

import com.artemissoftware.beerace.core.data.remote.dto.CaptchaDto
import com.artemissoftware.beerace.core.data.remote.dto.ErrorDto
import com.artemissoftware.beerace.core.domain.Resource
import com.artemissoftware.beerace.core.domain.error.DataError
import com.artemissoftware.beerace.feature.race.data.mapper.toCaptcha
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import retrofit2.HttpException

internal object HandleNetwork {

    inline fun <T> safeNetworkCall(apiCall: () -> T): Resource<T> {
        return try {
            Resource.Success(data = apiCall())
        } catch (ex: Exception) {
            Resource.Failure(error = handleException(ex))
        }
    }

    fun handleException(ex: Exception): DataError {
        return when (ex) {
            is HttpException -> {
                convertErrorBody(ex)
            }
            is UnknownHostException -> {
                DataError.NetworkError.UnknownHost
            }

            is ConnectException -> {
                DataError.NetworkError.Connect
            }

            is SocketTimeoutException -> {
                DataError.NetworkError.SocketTimeout
            }
            is CancellationException -> {
                throw ex
            }
            else -> DataError.NetworkError.Unknown
        }
    }

    private fun convertErrorBody(httpException: HttpException): DataError.NetworkError {
        return try {
            httpException.response()?.errorBody()?.let {

                val errorBody = it.string()
                val moshi: Moshi = Moshi.Builder().build()
                val adapter: JsonAdapter<ErrorDto> = moshi.adapter(ErrorDto::class.java)
                val adapterCaptcha: JsonAdapter<CaptchaDto> = moshi.adapter(CaptchaDto::class.java)

                val error = when {
                    errorBody.contains("error") -> DataError.NetworkError.Error(
                        message = (adapter.fromJson(errorBody)!!).error.message
                    )
                    errorBody.contains("captchaUrl") -> DataError.NetworkError.CaptchaControl(
                        captcha = (adapterCaptcha.fromJson(errorBody)!!).toCaptcha()
                    )
                    else -> DataError.NetworkError.Unknown
                }

                error
            } ?: kotlin.run {
                DataError.NetworkError.Unknown
            }
        } catch (exception: Exception) {
            DataError.NetworkError.Unknown
        }
    }
}