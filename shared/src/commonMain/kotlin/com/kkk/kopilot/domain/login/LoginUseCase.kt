package com.kkk.kopilot.domain.login

import com.kkk.kopilot.domain.login.model.LoginUseCaseStateModel

interface LoginUseCase {
    suspend operator fun invoke(
        emailOrPhoneNumber: String,
        password: String
    ): LoginUseCaseStateModel
}