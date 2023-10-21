package com.kkk.kopilot.domain.login.model

import com.kkk.kopilot.datas.common.remote.api.model.DefaultFailureCauseModel

sealed class LoginUseCaseStateModel {
    data object Success : LoginUseCaseStateModel()
    data class Failure(val cause: DefaultFailureCauseModel) : LoginUseCaseStateModel()
}