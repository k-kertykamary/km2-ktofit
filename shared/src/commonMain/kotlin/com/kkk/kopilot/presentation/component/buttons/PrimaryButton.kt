package com.kkk.kopilot.presentation.component.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kkk.kopilot.presentation.component.loader.loadingindicator.AnimationType
import com.kkk.kopilot.presentation.component.loader.loadingindicator.LoadingIndicator
import com.kkk.kopilot.presentation.component.loader.loadingindicator.LoadingIndicatorSize
import com.kkk.kopilot.presentation.theme.Dimensions

@Composable
fun PrimaryButton(
    modifier: Modifier,
    text: String,
    loading: Boolean = false,
    animationType: AnimationType = AnimationType.Bounce,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .height(Dimensions.buttonHeight),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            if (loading) {
                LoadingIndicator(
                    animationType = animationType,
                    color = MaterialTheme.colors.surface,
                    size = LoadingIndicatorSize.Small
                )
            } else {
                Box {
                    Text(text = text, style = MaterialTheme.typography.button)
                }
            }
        }
    }
}