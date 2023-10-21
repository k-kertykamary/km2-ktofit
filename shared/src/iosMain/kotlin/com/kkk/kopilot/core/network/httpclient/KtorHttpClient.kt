package com.kkk.kopilot.core.network.httpclient

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun ktorHttpClient(
    config: HttpClientConfig<*>.() -> Unit
) = HttpClient(Darwin) {
    config(this)

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }

    }
}