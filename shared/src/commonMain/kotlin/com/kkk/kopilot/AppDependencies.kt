package com.kkk.kopilot

import com.kkk.kopilot.core.di.coreModules
import com.kkk.kopilot.datas.di.dataModules
import com.kkk.kopilot.di.navigationModule
import com.kkk.kopilot.domain.di.domainModules
import com.kkk.kopilot.presentation.di.presentationModules
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun KoinApplication.Companion.start(): KoinApplication = initKoin {}

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        modules(
            coreModules,
            navigationModule,
            dataModules,
            domainModules,
            presentationModules
        )
        appDeclaration()
    }
}