package com.kkk.kopilot.core.network.di

import com.kkk.kopilot.core.logger.KermitKtorLogger
import com.kkk.kopilot.core.network.httpclient.HttpClients
import com.kkk.kopilot.core.network.httpclient.configurator.HeaderConfigurator
import com.kkk.kopilot.core.network.httpclient.ktorHttpClient
import com.kkk.kopilot.core.network.httpclient.mockKtorHttpClient
import io.github.aakira.napier.Napier
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val TIME_OUT = 60_000L

@OptIn(ExperimentalSerializationApi::class)
val networkModules = module {
    single<Logger> {
        KermitKtorLogger
    }

    single {
        HeaderConfigurator(get())
    }

    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single(named(HttpClients.REGULAR_HTTP_CLIENT)) {
        val headerConfig: HeaderConfigurator = get()
        ktorHttpClient {
            expectSuccess = true
            developmentMode = true

            install(ContentNegotiation) {
                json(get())
            }

            defaultRequest {
                headerConfig.configure(this)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
            }

            install(Logging) {
                logger = get()
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Napier.d("HTTP status: - ${response.status.value}", tag = "Ktor")
                    Napier.d("HTTP response: $response", tag = "Ktor")
                }
            }
        }
    }

    single(named(HttpClients.MOCK_ENGINE_HTTP_CLIENT)) {
        mockKtorHttpClient(
            getAll()
        )
    }

    includes(ktorFitModule)
}