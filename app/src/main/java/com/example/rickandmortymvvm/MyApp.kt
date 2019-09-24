package com.example.rickandmortymvvm

import android.app.Application
import com.example.rickandmortymvvm.di.DiModulesBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(DiModulesBuilder.buildModules())
        }
    }
}