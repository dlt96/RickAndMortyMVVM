package com.example.rickandmortymvvm

import android.app.Application
import com.example.rickandmortymvvm.di.DiModulesBuilder
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@MyApp)
            modules(DiModulesBuilder.buildModules())
        }
    }
}