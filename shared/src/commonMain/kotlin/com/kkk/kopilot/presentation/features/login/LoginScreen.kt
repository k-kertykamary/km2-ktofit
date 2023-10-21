package com.kkk.kopilot.presentation.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kkk.kopilot.presentation.component.loader.Loader
import com.kkk.kopilot.presentation.component.toast.ToastDefaults
import com.kkk.kopilot.presentation.features.home.HomeScreen
import com.kkk.kopilot.presentation.features.login.components.LoginContent
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenUIModel
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenViewModel
import com.kkk.kopilot.presentation.features.lostpassword.LostPasswordScreen
import com.kkk.kopilot.presentation.features.signup.SignUpScreen
import dev.icerock.moko.resources.compose.stringResource
import org.koin.mp.KoinPlatform.getKoin

class LoginScreen : Screen {
    private val screenModel: LoginScreenViewModel = getKoin().get()

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val state: LoginScreenViewModel.State by screenModel.state.collectAsState()

        when (state) {
            is LoginScreenViewModel.State.Loading -> Loader()

            is LoginScreenViewModel.State.Init, is LoginScreenViewModel.State.Connecting, is LoginScreenViewModel.State.Error -> {
                val uiModel = when (state) {
                    is LoginScreenViewModel.State.Init -> (state as LoginScreenViewModel.State.Init).uiModel
                    is LoginScreenViewModel.State.Connecting -> (state as LoginScreenViewModel.State.Connecting).uiModel
                    is LoginScreenViewModel.State.Error -> (state as LoginScreenViewModel.State.Error).uiModel
                    else -> {
                        return
                    }
                }

                LoginContent(
                    uiModel = uiModel,
                    onLoginClicked = { emailOrPhoneNumber, password ->
                        screenModel.login(
                            emailOrPhoneNumber = emailOrPhoneNumber,
                            password = password,
                            success = {
                                navigator.replace(HomeScreen())
                            })
                    },
                    onLostPasswordClicked = {
                        navigator.push(LostPasswordScreen())
                    },
                    onSignUpClicked = {
                        navigator.push(SignUpScreen())
                    }
                )

                showErrorIfNeed(uiModel)
            }
        }
    }

    @Composable
    fun showErrorIfNeed(uiModel: LoginScreenUIModel) {
        uiModel.error?.let {
            val errorMessage = stringResource(it)
            ToastDefaults.error(
                message = errorMessage,
                onClose = {
                    screenModel.reInit(
                        emailOrPhoneNumber = uiModel.emailOrPhoneNumber,
                        password = uiModel.password
                    )
                }
            )
        }
    }
}

