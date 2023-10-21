package com.kkk.kopilot.core.network.api.models

import com.kkk.kopilot.datas.common.remote.api.model.DefaultFailureCauseModel
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException

sealed class ApiException : Throwable() {
    data class Default(
        val sourceThrowable: Throwable,
        val errorBodyString: String? = null,
    ) : ApiException()

    data class NoInternet(
        val sourceThrowable: Throwable
    ) : ApiException()

    data class ServerError(
        val httpCode: Int,
        val errorBodyString: String,
    ) : ApiException()

    data class ClientError(
        val httpCode: Int,
        val errorBodyString: String,
    ) : ApiException()
}

fun IOException.parseToNoInternet() = ApiException.NoInternet(sourceThrowable = this)

fun Throwable.parseToDefault() = ApiException.Default(sourceThrowable = this)

suspend fun Throwable.parseToException() = try {
    val response = (this as ResponseException).response
    ApiException.ServerError(
        httpCode = response.status.value,
        errorBodyString = response.bodyAsText(),
    )
} catch (e: Exception) {
    Throwable(message = this.message).parseToDefault()
}


suspend fun Throwable.parseToClientException() = try {
    val response = (this as ResponseException).response
    ApiException.ClientError(
        httpCode = response.status.value,
        errorBodyString = response.bodyAsText(),
    )
} catch (e: Exception) {
    Throwable(message = this.message).parseToDefault()
}


fun ApiException.mapToDefaultFailureCauseModel(): DefaultFailureCauseModel {
    return when(this) {
        is ApiException.Default -> DefaultFailureCauseModel.Unknown(this.sourceThrowable)
        is ApiException.NoInternet -> DefaultFailureCauseModel.NoInternet(this.sourceThrowable)
        is ApiException.ServerError -> DefaultFailureCauseModel.InternetError(
            this.httpCode,
            this.errorBodyString
        )
        is ApiException.ClientError -> DefaultFailureCauseModel.InternetError(
            this.httpCode,
            this.errorBodyString
        )

    }
}
