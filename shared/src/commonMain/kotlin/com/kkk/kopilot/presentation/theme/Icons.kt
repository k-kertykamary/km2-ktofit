package com.kkk.kopilot.presentation.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.kkk.kopilot.MR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

object KopilotIcons {
    val email: @Composable () -> Unit
        get() = {
            Icon(
                modifier = Modifier.size(Dimensions.iconSize),
                imageVector = Icons.Filled.Email,
                contentDescription = "Email Icon"
            )
        }

    val password: @Composable () -> Unit
        get() = {
            Icon(
                modifier = Modifier
                    .size(Dimensions.iconSize),
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock Icon"
            )
        }

    val error: @Composable () -> Unit
        get() = {
            Icon(
                modifier = Modifier
                    .size(Dimensions.iconSize),
                painter = painterResource(MR.images.ic_error),
                contentDescription = "Error Icon"
            )
        }

    val warning: @Composable () -> Unit
        get() = {
            Icon(
                modifier = Modifier
                    .size(Dimensions.iconSize),
                painter = painterResource(MR.images.ic_warning),
                contentDescription = "Warning Icon"
            )
        }

    val info: @Composable () -> Unit
        get() = {
            Icon(
                modifier = Modifier
                    .size(Dimensions.iconSize),
                painter = painterResource(MR.images.ic_info),
                contentDescription = "Info Icon"
            )
        }

    @Composable
    fun eyes(actionClick: (() -> Unit)? = null) =
        Icon(
            modifier = Modifier
                .size(Dimensions.iconSize)
                .clickable {
                    actionClick?.invoke()
                },
            painter = painterResource(MR.images.ic_visibility),
            contentDescription = "Visibility Icon",
        )

    @Composable
    fun eyesOff(actionClick: (() -> Unit)? = null) =
        Icon(
            modifier = Modifier
                .size(Dimensions.iconSize)
                .clickable {
                    actionClick?.invoke()
                },
            painter = painterResource(MR.images.ic_visibility_off),
            contentDescription = "Visibility Off Icon",
        )

    @Composable
    fun get(
        icon: ImageResource,
        tint: Color = MaterialTheme.colors.primary,
        size: Dp = Dimensions.iconSize
    ) =
        Icon(
            modifier = Modifier
                .size(size),
            painter = painterResource(icon),
            contentDescription = icon.toString(),
            tint = tint
        )
}


