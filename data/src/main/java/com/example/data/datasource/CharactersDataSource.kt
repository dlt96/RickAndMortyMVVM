package com.example.data.datasource

import androidx.annotation.WorkerThread
import com.example.data.model.CharacterPageEntity

interface CharactersDataSource {

    @WorkerThread
    suspend fun fetchCharacters(page: Int): CharacterPageEntity?
}