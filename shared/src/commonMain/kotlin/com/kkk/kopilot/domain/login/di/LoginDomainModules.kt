package com.kkk.kopilot.domain.login.di

import com.kkk.kopilot.domain.login.LoginUseCase
import com.kkk.kopilot.domain.login.LoginUseCaseImpl
import com.kkk.kopilot.domain.login.mapper.LoginUseCaseMapper
import org.koin.dsl.module

val loginDomainModules = module {

    factory { LoginUseCaseMapper() }

    factory<LoginUseCase> {
        LoginUseCaseImpl(
            get(),
            get(),
        )
    }
}