package com.example.rickandmortymvvm.di

object DiModulesBuilder {
    fun buildModules() = listOf(
        DataModules.remoteServiceModule,
        DataModules.apiModule,
        DataModules.repository,
        DataModules.errorHandler,
        DomainModules.useCaseModules,
        PresentationModules.mainModule
    )
}