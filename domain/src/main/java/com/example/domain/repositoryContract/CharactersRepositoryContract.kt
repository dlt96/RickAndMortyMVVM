package com.example.domain.repositoryContract

import com.example.domain.model.CharacterListModel

interface CharactersRepositoryContract {

    suspend fun getCharactersPage(page : Int) : CharacterListModel
}