package com.kkk.kopilot.datas.login.remote.api

import com.kkk.kopilot.core.network.api.models.NetworkResponse
import com.kkk.kopilot.datas.login.remote.LoginConstant
import com.kkk.kopilot.datas.login.remote.api.model.LoginResponseModel
import com.kkk.kopilot.datas.login.remote.api.requestmodel.LoginRequestModel
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface LoginApi {
    @POST(LoginConstant.loginEndPoint)
    suspend fun login(@Body requestModel: LoginRequestModel): NetworkResponse<LoginResponseModel>
}