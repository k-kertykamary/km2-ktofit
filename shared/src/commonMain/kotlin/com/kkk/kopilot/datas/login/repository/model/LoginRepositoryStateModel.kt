package com.kkk.kopilot.datas.login.repository.model

import com.kkk.kopilot.datas.common.remote.api.model.DefaultFailureCauseModel

sealed class LoginRepositoryStateModel {
    data class Success(val accessToken: String) : LoginRepositoryStateModel()
    data class Failure(val cause: DefaultFailureCauseModel) : LoginRepositoryStateModel()

}