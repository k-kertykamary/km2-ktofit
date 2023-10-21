package com.kkk.kopilot.presentation.features.splash.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kkk.kopilot.MR
import com.kkk.kopilot.core.animation.ShakeConfig
import com.kkk.kopilot.core.animation.rememberShakeController
import com.kkk.kopilot.core.animation.shake
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun SplashContent(
    version: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Logo()
        Text(
            text = "version $version",
            style = MaterialTheme.typography.overline,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
        )
    }
}

@Composable
fun Logo(animated: Boolean = false) {

    val shakeController = rememberShakeController()
    if (animated) {
        val shakeConfig = ShakeConfig()
        LaunchedEffect(Unit){
            shakeController.shake(shakeConfig)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shake(shakeController),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(MR.images.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
        )

        Text(
            text = stringResource(MR.strings.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}