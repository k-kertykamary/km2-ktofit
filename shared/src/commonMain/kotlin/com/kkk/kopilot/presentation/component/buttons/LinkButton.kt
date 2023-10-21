package com.kkk.kopilot.presentation.component.buttons

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun LinkButton(
    modifier: Modifier,
    text: String,
    onClick: (Int) -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(text)
        addStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.Underline,
            ),
            start = 0,
            end = text.length
        )
    }

    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun LinkButton(
    modifier: Modifier,
    annotatedString: AnnotatedString,
    onClick: (Int) -> Unit
) {
    ClickableText(
        text = annotatedString,
        modifier = modifier,
        onClick = onClick
    )
}