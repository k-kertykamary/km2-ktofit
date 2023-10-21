package com.kkk.kopilot.datas.common.remote.api.model

sealed class DefaultFailureCauseModel {
    data class Unknown(val sourceThrowable: Throwable): DefaultFailureCauseModel()
    data class NoInternet(val sourceThrowable: Throwable): DefaultFailureCauseModel()
    data class InternetError(val httpCode: Int, val body: String): DefaultFailureCauseModel()
}