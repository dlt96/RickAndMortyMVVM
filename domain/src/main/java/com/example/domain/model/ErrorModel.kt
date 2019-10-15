package com.example.domain.model

interface ErrorHandlerContract {
    fun getError(throwable: Throwable): ErrorModel
}

sealed class ErrorModel {

    abstract val originalException: Throwable

    data class Network(override val originalException: Throwable) : ErrorModel()

    data class NotFound(override val originalException: Throwable) : ErrorModel()

    data class AccessDenied(override val originalException: Throwable) : ErrorModel()

    data class ServiceUnavailable(override val originalException: Throwable) : ErrorModel()

    data class Unknown(override val originalException: Throwable) : ErrorModel()
}