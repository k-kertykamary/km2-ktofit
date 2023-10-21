package com.kkk.kopilot.datas.login.repository

import com.kkk.kopilot.core.network.api.safeApiCall
import com.kkk.kopilot.datas.login.remote.api.LoginApi
import com.kkk.kopilot.datas.login.repository.mapper.LoginRepositoryMapper
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryModel
import com.kkk.kopilot.datas.login.repository.model.LoginRepositoryStateModel
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import org.koin.mp.KoinPlatform


interface ExampleApi {
    @GET("people/1/")
    suspend fun getPerson(): String
}

class LoginRepositoryImpl(
    private val mapper: LoginRepositoryMapper
): LoginRepository {
    override suspend fun login(loginRepositoryModel: LoginRepositoryModel): LoginRepositoryStateModel {
        val requestModel = mapper.mapToRequestModel(loginRepositoryModel)
        val loginApi: LoginApi = KoinPlatform.getKoin().get()
        val response = safeApiCall {
            loginApi.login(requestModel)
        }
        val state = mapper.mapToRepositoryStateModel(response)
        if (state is LoginRepositoryStateModel.Success) save(state.accessToken)
        return state
    }

    override fun save(accessToken: String) {
    }
}