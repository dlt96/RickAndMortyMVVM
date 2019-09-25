package com.example.data.api

import com.example.data.service.RickAndMortyService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RickAndMortyServiceAPI {

    fun getRickAndMortyService() : RickAndMortyService

    {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        return retrofit.create(RickAndMortyService::class.java)
    }
}