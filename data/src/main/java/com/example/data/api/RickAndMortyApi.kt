package com.example.data.api

import androidx.annotation.WorkerThread
import com.example.data.datasource.CharactersDataSource
import com.example.data.entity.CharacterPageEntity
import com.example.data.service.RickAndMortyService

class RickAndMortyApi(private val service: RickAndMortyService): CharactersDataSource {

    @WorkerThread
    override suspend fun fetchCharacters(page: Int) = CharacterPageEntity(
        service.getCharacters(page),
        page
    )
}