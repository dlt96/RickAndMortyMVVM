package com.example.rickandmortymvvm.di

object DiModulesBuilder {
    fun buildModules() = listOf(
        DataModules.remoteServiceModule
//        DomainModules,
//        PresentationModules
    )
}