package com.kkk.kopilot.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import com.kkk.kopilot.MR
import dev.icerock.moko.resources.compose.fontFamilyResource

@Composable
fun KopilotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme)
        darkColorPalette
    else
        lightColorPalette

    val quickSandFontFamily: FontFamily = fontFamilyResource(MR.fonts.Quicksand.medium)
    val typography = kopilotTypography(quickSandFontFamily)

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes(
            small = RoundedCornerShape(Dimensions.smallShape),
            medium = RoundedCornerShape(Dimensions.mediumShape),
            large = RoundedCornerShape(Dimensions.largeShape)
        )
    ) {
        content()
    }
}