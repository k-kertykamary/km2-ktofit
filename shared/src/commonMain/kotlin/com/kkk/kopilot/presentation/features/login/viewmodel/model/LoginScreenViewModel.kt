package com.kkk.kopilot.presentation.features.login.viewmodel.model

import cafe.adriel.voyager.core.model.StateScreenModel
import com.kkk.kopilot.domain.login.LoginUseCase
import com.kkk.kopilot.domain.login.model.LoginUseCaseStateModel
import com.kkk.kopilot.presentation.features.login.viewmodel.mapper.LoginScreenMapperModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val mapper: LoginScreenMapperModel,
    private val useCase: LoginUseCase,
    private val coroutineScope: CoroutineScope
) : StateScreenModel<LoginScreenViewModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()
        data class Init(val uiModel: LoginScreenUIModel) : State()
        data class Connecting(val uiModel: LoginScreenUIModel) : State()
        data class Error(val uiModel: LoginScreenUIModel) : State()
    }

    init {
        coroutineScope.launch {
            mutableState.emit(
                State.Init(
                    LoginScreenUIModel()
                )
            )
        }
    }

    fun login(emailOrPhoneNumber: String, password: String, success: () -> Unit) {
        coroutineScope.launch {
            val newState = mapper.map(emailOrPhoneNumber, password)

            mutableState.update {
                newState
            }

            if (newState is State.Connecting) {
                val param1 = newState.uiModel.emailOrPhoneNumber
                val param2 = newState.uiModel.password

                // can also call .invoke
                when(val case = useCase(param1, param2)) {
                    is LoginUseCaseStateModel.Failure -> {
                        mutableState.emit(mapper.mapError(emailOrPhoneNumber, password, case.cause))
                    }
                    is LoginUseCaseStateModel.Success -> {
                        success()
                    }
                }
            }
        }
    }

    fun reInit(emailOrPhoneNumber: String, password: String) {
        coroutineScope.launch {
            mutableState.emit(
                State.Init(
                    LoginScreenUIModel(
                        emailOrPhoneNumber = emailOrPhoneNumber,
                        password = password
                    )
                )
            )
        }
    }
}