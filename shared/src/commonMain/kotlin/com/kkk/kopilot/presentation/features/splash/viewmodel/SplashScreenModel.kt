package com.kkk.kopilot.presentation.features.splash.viewmodel

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenModel(coroutineScope: CoroutineScope): StateScreenModel<SplashScreenModel.State>(State.Loading) {
    sealed class State {
        data object Loading : State()
        data class Start(val version: String) : State()
        data object End : State()
    }

    private val splashDelay = 3000L
    init {
        coroutineScope.launch {
            //TODO : Get Version from Platform expect/actual Or BuildKonfig
            mutableState.emit(State.Start("0.1"))
            // If need do all config here
            delay(splashDelay)
            mutableState.emit(State.End)
        }
    }

}
