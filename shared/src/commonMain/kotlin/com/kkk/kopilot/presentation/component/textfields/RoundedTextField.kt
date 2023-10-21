package com.kkk.kopilot.presentation.component.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.kkk.kopilot.presentation.theme.Dimensions


@Composable
fun RoundedTextField(
    modifier: Modifier,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var stateValue by remember { mutableStateOf(value) }

    BasicTextField(
        value = stateValue,
        onValueChange = {
            stateValue = it
            onValueChange.invoke(stateValue)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.textFieldHeight)
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = Dimensions.textFieldBorder,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium
            )
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = Dimensions.textFieldPaddingHorizontal,
                vertical = Dimensions.textFieldPaddingVertical
                ),
        textStyle = MaterialTheme.typography.caption,
        singleLine = true,
        decorationBox = { innerTextField ->
            Box {
                if (stateValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.primaryVariant
                    )
                }
                Spacer(modifier = Modifier.width(width = 8.dp))
                innerTextField()
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation
    )
}

@Composable
fun RoundedIconTextField(
    modifier: Modifier,
    icon : @Composable () -> Unit,
    trailingIcon : (@Composable () -> Unit)? = null,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon.invoke()
        Spacer(modifier = Modifier.width(Dimensions.spacingS))
        RoundedTextField(
            modifier = Modifier.weight(1f),
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation
        )
        trailingIcon?.let {
            Spacer(modifier = Modifier.width(Dimensions.spacingS))
            it.invoke()
        }
    }
}