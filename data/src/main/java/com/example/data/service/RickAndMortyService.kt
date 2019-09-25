package com.example.data.service

import com.example.data.entity.CharacterListEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface RickAndMortyService {

    @GET("api/character/")
    fun getCharacters (@Query("page") pageNumber: Int):
            Deferred<Response<CharacterListEntity>>

}