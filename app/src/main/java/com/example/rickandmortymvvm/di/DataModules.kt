package com.example.rickandmortymvvm.di

import com.example.data.api.RickAndMortyServiceAPI
import org.koin.dsl.module

object DataModules {

    val remoteServiceModule = module {
        single {
            RickAndMortyServiceAPI()
        }
    }
}
