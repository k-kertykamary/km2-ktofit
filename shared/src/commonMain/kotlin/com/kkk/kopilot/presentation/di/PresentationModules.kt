package com.kkk.kopilot.presentation.di

import com.kkk.kopilot.presentation.features.login.di.loginFeatureModules
import org.koin.dsl.module

val presentationModules = module {
    includes(
        listOf(
            loginFeatureModules
        )
    )
}