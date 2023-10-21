package com.kkk.kopilot.core.async.di

import com.kkk.kopilot.core.async.DispatchersNames
import com.kkk.kopilot.core.async.ScopesNames
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val dispatchersModule = module {

    single(named(ScopesNames.ALWAYS_ALIVE_SCOPE)) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    single(named(ScopesNames.PROFILE_SESSION_SCOPE)) {
        CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    single(named(DispatchersNames.DOMAIN)) {
        Dispatchers.Default
    }

    single(named(DispatchersNames.UI_VIEWMODEL)) {
        SupervisorJob() + Dispatchers.Default
    }

    single(named(ScopesNames.VIEW_MODEL_SCOPE)) {
        CoroutineScope(get(named(DispatchersNames.UI_VIEWMODEL)))
    }

    single(named(DispatchersNames.UI_LAYOUT)) {
        Dispatchers.Main
    } bind CoroutineDispatcher::class


}