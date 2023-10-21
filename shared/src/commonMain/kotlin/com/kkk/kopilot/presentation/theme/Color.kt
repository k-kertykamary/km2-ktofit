package com.kkk.kopilot.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val lightColorPalette = lightColors(
    primary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    onPrimary = Color.White,
    error = Color.Red,
    primaryVariant = Color.LightGray
)

val darkColorPalette = darkColors(
    primary = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    onPrimary = Color.Black,
    error = Color.Red,
    primaryVariant = Color.LightGray
)

val Colors.warning: Color
    get() = Color(0XFFEC942C)