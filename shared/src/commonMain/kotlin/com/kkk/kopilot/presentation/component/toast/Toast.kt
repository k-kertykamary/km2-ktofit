package com.kkk.kopilot.presentation.component.toast

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.kkk.kopilot.MR
import com.kkk.kopilot.presentation.theme.Dimensions
import com.kkk.kopilot.presentation.theme.KopilotIcons
import com.kkk.kopilot.presentation.theme.warning
import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class ToastDuration(val value: Long) {
    SHORT(1000),
    MEDIUM(1500),
    LONG(2000)
}

enum class ToastStyle {
    ERROR,
    WARNING,
    INFO,
}

@Composable
fun Toast(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    duration: ToastDuration = ToastDuration.MEDIUM,
    style: ToastStyle = ToastStyle.WARNING,
    onClose: (() -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = showDialog,
        // TODO: Custom animation here
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Popup(
            onDismissRequest = {},
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(getPrimaryColorFromStyle(style))
                ) {
                    Row(
                        modifier = Modifier.padding(
                            vertical = Dimensions.spacing,
                            horizontal = Dimensions.spacing
                        ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        KopilotIcons.get(
                            icon = getIconFromStyle(style),
                            tint = MaterialTheme.colors.surface,
                            size = Dimensions.iconSizeL
                        )
                        Spacer(modifier = Modifier.width(Dimensions.spacing))
                        Column {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colors.surface
                            )
                            Spacer(modifier = Modifier.width(Dimensions.spacing))
                            Text(
                                text = message,
                                style = MaterialTheme.typography.overline,
                                textAlign = TextAlign.Justify,
                                color = MaterialTheme.colors.surface
                            )
                        }
                    }
                }
            }
        }
    }

    coroutineScope.launch {
        delay(duration.value)
        showDialog = false
        onClose?.invoke()
    }
}


@Composable
fun getPrimaryColorFromStyle(style: ToastStyle): Color {
    return when(style) {
        ToastStyle.ERROR -> MaterialTheme.colors.error
        ToastStyle.WARNING -> MaterialTheme.colors.warning
        ToastStyle.INFO -> MaterialTheme.colors.primary
    }
}

@Composable
fun getIconFromStyle(style: ToastStyle): ImageResource {
    return when(style) {
        ToastStyle.ERROR -> MR.images.ic_error
        ToastStyle.WARNING -> MR.images.ic_warning
        ToastStyle.INFO -> MR.images.ic_info
    }
}

