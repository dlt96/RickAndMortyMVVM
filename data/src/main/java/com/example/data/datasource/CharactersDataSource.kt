package com.example.data.datasource

import androidx.annotation.WorkerThread
import com.example.data.entity.CharacterPageEntity
import kotlinx.coroutines.Deferred

interface CharactersDataSource {

    @WorkerThread
    fun fetchCharacters(page: Int): Deferred<CharacterPageEntity?>
}