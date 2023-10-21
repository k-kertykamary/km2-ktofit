package com.kkk.kopilot.datas.common.remote.mock

import com.kkk.kopilot.core.extensions.Empty
import com.kkk.kopilot.datas.common.resourcereader.ResourceReader
import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

abstract class MockApiHandler {
    abstract var resourceJsonFile: String
    abstract var httpStatusCode: HttpStatusCode

    fun handleRequest(scope: MockRequestHandleScope, request: HttpRequestData) : HttpResponseData? {
        val responseHeaders =
            headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
        val jsonFile = try {
            ResourceReader().read(resourceJsonFile)
        } catch (t: Throwable) {
            String.Empty
        }
        return scope.respond(jsonFile, httpStatusCode, responseHeaders)
    }
}