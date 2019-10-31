package com.example.rickandmortymvvm.di

import com.example.domain.usecase.GetCharacterListCase
import com.example.domain.usecaseContract.GetCharacterListCaseContract
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object DomainModules {

    val useCaseModules = module {
        single {
            GetCharacterListCase(
                repo = get(),
                dispatcher = Dispatchers.IO
            ) as GetCharacterListCaseContract
        }
    }
}