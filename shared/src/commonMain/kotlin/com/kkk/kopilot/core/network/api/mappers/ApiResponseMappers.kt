package com.kkk.kopilot.core.network.api.mappers

import com.kkk.kopilot.core.network.api.models.NetworkResponse
import com.kkk.kopilot.core.network.api.models.Response
import com.kkk.kopilot.core.network.api.models.asCustomResponseFailure
import com.kkk.kopilot.core.network.api.models.asCustomResponseSuccess
import com.kkk.kopilot.core.network.api.models.parseToDefault
import com.kkk.kopilot.core.network.api.models.parseToNoInternet
import io.ktor.utils.io.errors.IOException

sealed class ApiResponseMappers<T> {
    companion object {
        fun <T> create(throwable: Throwable): NetworkResponse<T> {
            return when (throwable) {
                is IOException -> throwable.parseToNoInternet().asCustomResponseFailure()
                else -> throwable.parseToDefault().asCustomResponseFailure()
            }
        }

        fun <T> create(response: Response<T>): NetworkResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                return body.asCustomResponseSuccess(headers)

            } else {
                Throwable(message = response.description).parseToDefault().asCustomResponseFailure()
            }
        }
    }
}
