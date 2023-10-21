package com.kkk.kopilot.datas.login.di

import com.kkk.kopilot.core.network.httpclient.HttpClients
import com.kkk.kopilot.datas.login.remote.api.LoginApi
import com.kkk.kopilot.datas.login.repository.LoginRepository
import com.kkk.kopilot.datas.login.repository.LoginRepositoryImpl
import com.kkk.kopilot.datas.login.repository.mapper.LoginRepositoryMapper
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginDataModules = module {

    single<LoginApi> {
        val ktorFit: Ktorfit = get(named(HttpClients.KTOR_HTTP_CLIENT_ID))
        ktorFit.create()
    }

    factory { LoginRepositoryMapper() }

    single <LoginRepository> {
        LoginRepositoryImpl(
            get()
        )
    }
}