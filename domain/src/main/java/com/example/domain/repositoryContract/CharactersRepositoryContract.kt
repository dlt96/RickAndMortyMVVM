package com.example.domain.repositoryContract

import com.example.domain.datatype.Either
import com.example.domain.model.CharacterListModel
import com.example.domain.model.ErrorModel

interface CharactersRepositoryContract {

    suspend fun getCharactersPage(page : Int) : Either<ErrorModel, CharacterListModel>
}