package com.kkk.kopilot.datas.login.repository.mapper

import com.kkk.kopilot.core.network.api.models.NetworkResponse
import com.kkk.kopilot.core.network.api.models.mapToDefaultFailureCauseModel
import com.kkk.kopilot.datas.login.remote.api.model.LoginResponseModel
import com.kkk.kopilot.datas.login.remote.api.requestmodel.LoginRequestModel
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryModel
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryStateModel

class LoginRepositoryMapper {
    fun mapToRequestModel(repositoryModel: LoginRepositoryModel) =
        LoginRequestModel(
            phoneNumberOrEmail = repositoryModel.emailOrPhoneNumber,
            password = repositoryModel.password,
        )

    fun mapToRepositoryStateModel(response: NetworkResponse<LoginResponseModel>): LoginRepositoryStateModel {
        return when(response) {
            is NetworkResponse.Success -> {
                LoginRepositoryStateModel.Success(response.value.accessToken)
            }
            is NetworkResponse.Failure -> {
                LoginRepositoryStateModel.Failure(response.cause.mapToDefaultFailureCauseModel())
            }
        }
    }
}