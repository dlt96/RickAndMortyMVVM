package com.example.data.repository

import com.example.data.datasource.CharactersDataSource
import com.example.data.mapper.CharacterListEntityMapper
import com.example.domain.datatype.*
import com.example.domain.model.ErrorModel
import com.example.domain.model.CharacterListModel
import com.example.domain.model.ErrorHandlerContract
import com.example.domain.repositoryContract.CharactersRepositoryContract


class CharactersRepository(
    private val api: CharactersDataSource,
    private val errorHandler: ErrorHandlerContract
) : CharactersRepositoryContract {

    override suspend fun getCharactersPage(page: Int): Either<ErrorModel, CharacterListModel> =
        either {
            checkNotNull(api.fetchCharacters(page))
        }.map { value ->
            CharacterListEntityMapper.transform(value.characterList)
        }.mapError { error ->
            errorHandler.getError(error)
        }
}