package com.example.domain.usecaseContract

import com.example.domain.datatype.Either
import com.example.domain.model.CharacterListModel
import java.lang.Exception

interface GetCharacterListCaseContract {

    suspend fun getPage(page: Int?): Either<Exception, CharacterListModel>
}