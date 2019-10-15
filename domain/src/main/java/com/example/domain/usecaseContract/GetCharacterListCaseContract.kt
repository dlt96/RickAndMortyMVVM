package com.example.domain.usecaseContract

import com.example.domain.datatype.Either
import com.example.domain.model.CharacterListModel
import com.example.domain.model.ErrorModel

interface GetCharacterListCaseContract {

    suspend fun getPage(page: Int?): Either<ErrorModel, CharacterListModel>
}