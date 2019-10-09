package com.example.rickandmortymvvm.di

object DiModulesBuilder {
    fun buildModules() = listOf(
        DataModules.remoteServiceModule,
        DataModules.apiModule,
        DataModules.repository,
        DomainModules.useCaseModules
//        DomainModules,
//        PresentationModules
    )
}