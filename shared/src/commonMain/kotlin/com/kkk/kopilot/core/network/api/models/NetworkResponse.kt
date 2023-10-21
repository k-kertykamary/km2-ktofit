package com.kkk.kopilot.core.network.api.models

sealed class NetworkResponse<out SUCCESS> {
    data class Success<out SUCCESS>(
        val value: SUCCESS,
        val headers: Set<Map.Entry<String, List<String>>>
    ) : NetworkResponse<SUCCESS>()

    data class Failure(val cause: ApiException) : NetworkResponse<Nothing>()

    companion object {
        fun <T> success(
            value: T,
            headers: Set<Map.Entry<String, List<String>>>
        ) = Success(value = value, headers = headers)

        fun failure(value: ApiException) = Failure(value)
    }
}

fun <T> T.asCustomResponseSuccess(headers: Set<Map.Entry<String, List<String>>>) =
    NetworkResponse.success(value = this, headers)

fun ApiException.asCustomResponseFailure() =
    NetworkResponse.failure(value = this)