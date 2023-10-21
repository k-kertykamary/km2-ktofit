package com.kkk.kopilot.core.network.httpclient.configurator

import com.kkk.kopilot.datas.common.source.accesstoken.AccessTokenDataSource
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType

class HeaderConfigurator constructor(
    private val accessTokenDataSource: AccessTokenDataSource?
)  {
    fun configure(requestBuilder: DefaultRequest.DefaultRequestBuilder) {
        requestBuilder.contentType(ContentType.Application.Json)
        accessTokenDataSource?.get()?.let {
            requestBuilder.header("Authorization", "Bearer $it")
        }
    }
}