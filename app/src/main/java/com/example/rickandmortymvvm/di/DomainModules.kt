package com.example.rickandmortymvvm.di

import com.example.domain.repositoryContract.CharactersRepositoryContract
import com.example.domain.usecase.GetCharacterListCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object DomainModules {

    val useCaseModules = module {
        single {
            GetCharacterListCase(
                get(),
                Dispatchers.IO
            ) as CharactersRepositoryContract
        }
    }
}