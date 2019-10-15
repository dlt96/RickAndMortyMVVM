package com.example.domain.usecase

import com.example.domain.datatype.Either
import com.example.domain.model.CharacterListModel
import com.example.domain.model.ErrorModel
import com.example.domain.repositoryContract.CharactersRepositoryContract
import com.example.domain.usecaseContract.GetCharacterListCaseContract
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCharacterListCase(
    private val repo: CharactersRepositoryContract,
    private val dispatcher: CoroutineDispatcher
) : GetCharacterListCaseContract {

    override suspend fun getPage(page: Int?): Either<ErrorModel, CharacterListModel> =
        withContext(dispatcher) {
            repo.getCharactersPage(page ?: 1)
        }

}