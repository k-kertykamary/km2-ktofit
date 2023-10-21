package com.kkk.kopilot.datas.login.remote.api.requestmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LoginRequestModel(
    @SerialName("phoneNumberOrEmail")
    val phoneNumberOrEmail: String,

    @SerialName("password")
    val password: String
)