package com.kkk.kopilot.presentation.features.login.di

import com.kkk.kopilot.core.async.ScopesNames
import com.kkk.kopilot.presentation.features.login.viewmodel.mapper.LoginScreenMapperModel
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginFeatureModules = module {

    factory { LoginScreenMapperModel() }

    factory {
        LoginScreenViewModel(
            get(),
            get(),
            get(named(ScopesNames.VIEW_MODEL_SCOPE)),
        )
    }
}