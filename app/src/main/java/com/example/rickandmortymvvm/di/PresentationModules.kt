package com.example.rickandmortymvvm.di

import com.example.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModules {
    val mainModule = module {
        viewModel { CharactersViewModel(getCharacterListCase = get()) }
    }
}