package com.kkk.kopilot.core.network.httpclient

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

actual fun ktorHttpClient(
    config: HttpClientConfig<*>.() -> Unit
) = HttpClient()