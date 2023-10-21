package com.kkk.kopilot.domain.login.mapper

import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryModel

class LoginUseCaseMapper {
    fun mapToRepository(emailOrPhoneNumber: String, password: String) =
        LoginRepositoryModel(
            emailOrPhoneNumber = emailOrPhoneNumber,
            password = password
        )
}