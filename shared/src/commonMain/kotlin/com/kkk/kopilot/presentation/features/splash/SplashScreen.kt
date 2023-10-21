package com.kkk.kopilot.presentation.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kkk.kopilot.presentation.component.loader.Loader
import com.kkk.kopilot.presentation.features.login.LoginScreen
import com.kkk.kopilot.presentation.features.splash.components.SplashContent
import com.kkk.kopilot.presentation.features.splash.viewmodel.SplashScreenModel

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()
        val screenModel = rememberScreenModel {
            SplashScreenModel(coroutineScope)
        }
        val state by screenModel.state.collectAsState()

        when (state) {
            is SplashScreenModel.State.Loading -> Loader()

            is SplashScreenModel.State.Start -> {
                val version = (state as SplashScreenModel.State.Start).version
                SplashContent(version)
            }

            is SplashScreenModel.State.End -> navigator.replace(LoginScreen())
        }
    }
}