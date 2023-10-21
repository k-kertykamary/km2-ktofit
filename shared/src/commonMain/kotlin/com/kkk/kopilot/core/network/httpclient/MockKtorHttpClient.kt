package com.kkk.kopilot.core.network.httpclient

import com.kkk.kopilot.datas.common.remote.mock.MockApiHandler
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

@OptIn(ExperimentalSerializationApi::class)
fun mockKtorHttpClient(
    apiHandlers: List<MockApiHandler>
) = HttpClient(MockEngine) {
    expectSuccess = true
    developmentMode = true

    install(DefaultRequest) {
        contentType(ContentType.Application.Json)
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }

    engine {
        addHandler { request ->
            val handleList = apiHandlers.mapNotNull { mockHandler ->
                mockHandler.handleRequest(this, request)
            }

            if (handleList.isNotEmpty()) {
                handleList.first()
            } else {
                error("Unhandled ${request.url.encodedPath}")
            }
        }
    }
}