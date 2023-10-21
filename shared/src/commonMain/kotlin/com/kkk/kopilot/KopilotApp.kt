package com.kkk.kopilot

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.kkk.kopilot.presentation.features.splash.SplashScreen
import com.kkk.kopilot.presentation.theme.KopilotTheme

@Composable
fun KopilotApp() {
    KopilotTheme {
        Navigator(
            SplashScreen()
        )
    }
}

