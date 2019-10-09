package com.example.rickandmortymvvm.di

import com.example.data.api.RickAndMortyApi
import com.example.data.api.RickAndMortyServiceAPI
import com.example.data.datasource.CharactersDataSource
import com.example.data.repository.CharactersRepository
import com.example.domain.repositoryContract.CharactersRepositoryContract
import org.koin.dsl.module

object DataModules {

    val remoteServiceModule = module {
        single {
            RickAndMortyServiceAPI().getRickAndMortyService()
        }
    }

    val apiModule = module {
        single {
            RickAndMortyApi(get()) as CharactersDataSource
        }
    }

    val repository = module {
        single() {
            CharactersRepository(get()) as CharactersRepositoryContract
        }
    }
}
