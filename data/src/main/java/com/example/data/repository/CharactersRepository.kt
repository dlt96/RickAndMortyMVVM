package com.example.data.repository

import com.example.data.datasource.CharactersDataSource
import com.example.data.entity.CharacterPageEntity
import com.example.data.mapper.CharacterListEntityMapper
import com.example.domain.model.CharacterListModel
import com.example.domain.repositoryContract.CharactersRepositoryContract


class CharactersRepository(private val api: CharactersDataSource) : CharactersRepositoryContract {

    override suspend fun getCharactersPage(page: Int): CharacterListModel {
        val characterPageEntity: CharacterPageEntity = checkNotNull(api.fetchCharacters(page))
        return CharacterListEntityMapper.transform(characterPageEntity.characterList)
    }

}