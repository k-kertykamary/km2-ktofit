package com.kkk.kopilot.domain.di

import com.kkk.kopilot.domain.login.di.loginDomainModules
import org.koin.dsl.module

val domainModules = module {
    includes(
        listOf(
            loginDomainModules
        )
    )
}