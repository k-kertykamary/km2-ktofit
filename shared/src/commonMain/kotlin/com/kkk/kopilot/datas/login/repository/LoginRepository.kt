package com.kkk.kopilot.datas.login.repository

import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryModel
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryStateModel

interface LoginRepository {
    suspend fun login(loginRepositoryModel: LoginRepositoryModel): LoginRepositoryStateModel
    fun save(accessToken: String)
}