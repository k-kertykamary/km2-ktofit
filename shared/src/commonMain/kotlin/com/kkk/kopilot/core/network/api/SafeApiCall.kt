package com.kkk.kopilot.core.network.api

import com.kkk.kopilot.core.network.api.models.ApiException
import com.kkk.kopilot.core.network.api.models.NetworkResponse
import com.kkk.kopilot.core.network.api.models.asCustomResponseFailure
import com.kkk.kopilot.core.network.api.models.parseToClientException
import com.kkk.kopilot.core.network.api.models.parseToDefault
import com.kkk.kopilot.core.network.api.models.parseToException
import com.kkk.kopilot.core.network.api.models.parseToNoInternet
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(call: suspend () -> NetworkResponse<T>): NetworkResponse<T> {
    return try {
        safeApiCallThrowable(call)
    } catch (e: ApiException) {
        println(e)
        e.asCustomResponseFailure()
    } catch (e: Throwable) {
        println("HTTP safeApiCall $e")
        e.parseToDefault().asCustomResponseFailure()
    }
}

@Throws(ApiException::class, CancellationException::class)
private suspend fun <T> safeApiCallThrowable(call: suspend () -> NetworkResponse<T>): NetworkResponse<T> {
    try {
        return call.invoke()
    } catch (throwable: Throwable) {
        when(throwable) {
            is ServerResponseException -> {
                throw throwable.parseToException()
            }
            is ClientRequestException -> {
                throw throwable.parseToClientException()
            }
            is SocketTimeoutException -> {
                throw throwable.parseToNoInternet()
            }
            is ApiException -> {
                throw throwable
            }
            else -> {
                throw throwable.parseToDefault()
            }
        }
    }
}