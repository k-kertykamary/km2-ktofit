package com.kkk.kopilot.presentation.component.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kkk.kopilot.MR
import com.kkk.kopilot.presentation.component.loader.loadingindicator.LoadingIndicator
import com.kkk.kopilot.presentation.theme.Dimensions
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun Loader(
    withOverlay: Boolean = false,
    text: String = stringResource(MR.strings.loading_text)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (withOverlay) Color.Black.copy(alpha = 0.5f) else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingIndicator()
            Spacer(modifier = Modifier.size(Dimensions.spacing))
            Text(text, style = MaterialTheme.typography.caption)
        }

    }
}