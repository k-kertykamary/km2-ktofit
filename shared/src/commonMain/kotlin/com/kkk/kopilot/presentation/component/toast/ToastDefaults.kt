package com.kkk.kopilot.presentation.component.toast

import androidx.compose.runtime.Composable
import com.kkk.kopilot.MR
import dev.icerock.moko.resources.compose.stringResource

object ToastDefaults {
    @Composable
    fun error(
        title: String = stringResource(MR.strings.error_title),
        message: String,
        onClose: (() -> Unit)?
    ) {
        Toast(
            title = title,
            message = message,
            duration = ToastDuration.LONG,
            style = ToastStyle.ERROR,
            onClose = onClose
        )
    }


    @Composable
    fun warning(
        title: String = stringResource(MR.strings.warning_title),
        message: String,
        onClose: (() -> Unit)?
    ) {
        Toast(
            title = title,
            message = message,
            duration = ToastDuration.LONG,
            style = ToastStyle.WARNING,
            onClose = onClose
        )
    }

    @Composable
    fun info(
        title: String = stringResource(MR.strings.info_title),
        message: String,
        onClose: (() -> Unit)?
    ) {
        Toast(
            title = title,
            message = message,
            duration = ToastDuration.LONG,
            style = ToastStyle.INFO,
            onClose = onClose
        )
    }
}