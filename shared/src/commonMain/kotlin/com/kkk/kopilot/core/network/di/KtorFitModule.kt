package com.kkk.kopilot.core.network.di

import com.kkk.kopilot.core.network.httpclient.HttpClients
import com.kkk.kopilot.core.network.httpclient.HttpConstants
import com.kkk.kopilot.core.network.httpclient.converter.CustomResponseConverter
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ktorFitModule = module {
    single(named(HttpClients.KTOR_HTTP_CLIENT_ID)) {
        Ktorfit.Builder()
            .httpClient(client = get(named(HttpClients.REGULAR_HTTP_CLIENT)))
            .baseUrl(url = HttpConstants.BASE_API_URL)
            .converterFactories(CustomResponseConverter())
            .build()
    }
}