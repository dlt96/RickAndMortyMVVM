package com.example.data.utils

import com.example.domain.model.ErrorHandlerContract
import com.example.domain.model.ErrorModel
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

class ErrorHandler : ErrorHandlerContract {
    override fun getError(throwable: Throwable): ErrorModel {
        return when (throwable) {
            is IOException -> ErrorModel.Network(throwable)
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorModel.NotFound(throwable)

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorModel.AccessDenied(throwable)

                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorModel.ServiceUnavailable(throwable)

                    else -> ErrorModel.Unknown(throwable)
                }
            }
            else -> ErrorModel.Unknown(throwable)
        }
    }
}