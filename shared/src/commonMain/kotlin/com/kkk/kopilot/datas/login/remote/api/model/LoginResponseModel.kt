package com.kkk.kopilot.datas.login.remote.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseModel (
    @SerialName("accessToken")
    val accessToken: String,

    @SerialName("tokenType")
    val tokenType: String
)