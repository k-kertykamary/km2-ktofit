package com.kkk.kopilot.core.network.api.mappers

import com.kkk.kopilot.core.network.api.models.Response
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo

internal suspend fun <T> HttpResponse.toCommonResponse(typeInfo: TypeInfo): Response<T> {
    val responseBody: T = this.body(typeInfo)

    return object : Response<T> {
        override val isSuccessful: Boolean
            get() = this@toCommonResponse.status.isSuccess()

        override val code: Int
            get() = this@toCommonResponse.status.value

        override val description: String
            get() = this@toCommonResponse.status.description

        override fun body(): T {
            return responseBody
        }

        override fun headers(): Set<Map.Entry<String, List<String>>> {
            return this@toCommonResponse.headers.entries()
        }
    }
}