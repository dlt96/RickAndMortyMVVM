package com.example.presentation.viewmodel

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

interface ViewModelScope : CoroutineScope {

    val dispatcher: CoroutineDispatcher

    val job: Job
}

fun viewModelScope() = object : ViewModelScope {
    override val dispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    override val job = Job()

    override val coroutineContext by lazy { dispatcher + job }
}