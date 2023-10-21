package com.kkk.kopilot.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

fun kopilotTypography(defaultFont: FontFamily = FontFamily.Default) =
    Typography(
        h1 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W900,
            fontSize = Dimensions.h1
        ),
        h2 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W800,
            fontSize = Dimensions.h2,
        ),
        h3 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W800,
            fontSize = Dimensions.h3
        ),
        h4 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W800,
            fontSize = Dimensions.h4,
        ),
        h5 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W800,
            fontSize = Dimensions.h5,
        ),
        h6 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W800,
            fontSize = Dimensions.h6,
        ),
        subtitle1 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W700,
            fontSize = Dimensions.subtitle1,
        ),
        subtitle2 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W700,
            fontSize = Dimensions.subtitle2,
        ),
        body1 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W600,
            fontSize = Dimensions.body1,
        ),
        body2 = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W500,
            fontSize = Dimensions.body2,
        ),
        button = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W700,
            fontSize = Dimensions.button,
        ),
        caption = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W400,
            fontSize = Dimensions.caption,
        ),
        overline = TextStyle(
            fontFamily = defaultFont,
            fontWeight = FontWeight.W300,
            fontSize = Dimensions.overline,
        )
    )