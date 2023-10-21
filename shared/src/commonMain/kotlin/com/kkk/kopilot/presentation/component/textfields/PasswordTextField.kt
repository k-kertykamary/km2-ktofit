package com.kkk.kopilot.presentation.component.textfields

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kkk.kopilot.presentation.theme.KopilotIcons

@Composable
fun PasswordTextField(
    modifier: Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    val eyesIconActionClick = {
        passwordVisibility = !passwordVisibility
    }

    RoundedIconTextField(
        modifier = modifier,
        value = value,
        icon = KopilotIcons.password,
        trailingIcon = {
            if (passwordVisibility) KopilotIcons.eyesOff(eyesIconActionClick) else KopilotIcons.eyes(
                eyesIconActionClick
            )
        },
        placeholder = placeholder,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions.Default,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
    )
}