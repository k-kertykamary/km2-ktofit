package com.kkk.kopilot.presentation.component.textfields

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.kkk.kopilot.presentation.theme.KopilotIcons

@Composable
fun EmailTextField(
    modifier: Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false
) {
    RoundedIconTextField(
        modifier = modifier,
        value = value,
        icon = KopilotIcons.email,
        placeholder = placeholder,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions.Default,
    )
}