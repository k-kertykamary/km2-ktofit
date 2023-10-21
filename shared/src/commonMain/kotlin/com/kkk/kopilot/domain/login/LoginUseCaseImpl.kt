package com.kkk.kopilot.domain.login

import com.kkk.kopilot.datas.login.repository.LoginRepository
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryStateModel
import com.kkk.kopilot.domain.login.mapper.LoginUseCaseMapper
import com.kkk.kopilot.domain.login.model.LoginUseCaseStateModel

class LoginUseCaseImpl(
    private val repository: LoginRepository,
    private val mapper: LoginUseCaseMapper
): LoginUseCase {
    override suspend fun invoke(emailOrPhoneNumber: String, password: String): LoginUseCaseStateModel {
        return when (val result = repository.login(mapper.mapToRepository(emailOrPhoneNumber, password))) {
            is LoginRepositoryStateModel.Success -> {
                LoginUseCaseStateModel.Success
            }
            is LoginRepositoryStateModel.Failure -> {
                LoginUseCaseStateModel.Failure(
                    cause = result.cause
                )
            }
        }
    }

}