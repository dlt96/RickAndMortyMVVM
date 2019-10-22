package com.example.presentation.mapper

import com.example.domain.model.ErrorModel
import com.example.presentation.model.ErrorDisplayModel


object ErrorDisplayMapper {

    fun transform(error : ErrorModel) : ErrorDisplayModel {
       return when (error) {
            is ErrorModel.Network -> ErrorDisplayModel("Network error, check your internet connection, please")
            is ErrorModel.AccessDenied -> ErrorDisplayModel("Access denied")
            is ErrorModel.Unknown -> ErrorDisplayModel("Some error happened, try again please")
            is ErrorModel.ServiceUnavailable -> ErrorDisplayModel("Our servers are a having a hard time, please try again later")
            is ErrorModel.NotFound -> ErrorDisplayModel("Service not found, check again later, please")
            else -> ErrorDisplayModel("Some error happened, try again please")
        }
    }
}