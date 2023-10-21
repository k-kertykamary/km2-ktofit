package com.kkk.kopilot.core.di

import com.kkk.kopilot.core.async.di.dispatchersModule
import com.kkk.kopilot.core.network.di.networkModules
import org.koin.dsl.module

val coreModules = module {
    includes(dispatchersModule)
    includes(networkModules)
}