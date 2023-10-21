package com.kkk.kopilot.presentation.features.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.kkk.kopilot.MR
import com.kkk.kopilot.presentation.component.buttons.LinkButton
import com.kkk.kopilot.presentation.component.buttons.PrimaryButton
import com.kkk.kopilot.presentation.component.textfields.EmailTextField
import com.kkk.kopilot.presentation.component.textfields.PasswordTextField
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenUIModel
import com.kkk.kopilot.presentation.theme.Dimensions
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.compose.stringResource
import dev.icerock.moko.resources.format


typealias OnLoginClicked = ((emailOrPhoneNumber: String, password: String) -> Unit)

@Composable
fun LoginContent(
    uiModel: LoginScreenUIModel,
    onLoginClicked: OnLoginClicked,
    onLostPasswordClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.spacingXL),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var emailOrPhoneNumber by remember { mutableStateOf(uiModel.emailOrPhoneNumber) }
        var password by remember { mutableStateOf(uiModel.password) }

        val appName = stringResource(MR.strings.app_name)
        val loginTitle = MR.strings.login_screen_title.format(appName)
        Text(
            text = loginTitle.localized(),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(Dimensions.spacingXXL))

        EmailTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailOrPhoneNumber,
            placeholder = stringResource(MR.strings.hint_email_phone_number),
            enabled = !uiModel.isConnecting,
            readOnly = uiModel.isConnecting,
            onValueChange = {
                emailOrPhoneNumber = it
            }
        )
        Spacer(modifier = Modifier.size(Dimensions.spacing))
        PasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            placeholder = stringResource(MR.strings.hint_password),
            enabled = !uiModel.isConnecting,
            readOnly = uiModel.isConnecting,
            onValueChange = {
                password = it
            }
        )
        Spacer(modifier = Modifier.size(Dimensions.spacingXL))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(MR.strings.login_button_title),
                loading = uiModel.isConnecting,
                onClick = {
                    if (!uiModel.isConnecting) {
                        onLoginClicked.invoke(emailOrPhoneNumber, password)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.size(Dimensions.spacingL))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            LinkButton(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(MR.strings.lost_password_button_title),
                onClick = {
                    if (!uiModel.isConnecting) {
                        onLostPasswordClicked.invoke()
                    }
                }
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LinkButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(Dimensions.spacingXXL),
            text = stringResource(MR.strings.signup_button_title),
            onClick = {
                if (!uiModel.isConnecting) {
                    onSignUpClicked.invoke()
                }
            })
    }
}