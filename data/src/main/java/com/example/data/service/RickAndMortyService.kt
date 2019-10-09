package com.example.data.service

import com.example.data.entity.CharacterListEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("api/character/")
    suspend fun getCharacters (@Query("page") pageNumber: Int): CharacterListEntity

}