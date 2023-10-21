package com.kkk.kopilot.datas.di

import com.kkk.kopilot.datas.common.di.commonDataModules
import com.kkk.kopilot.datas.login.di.loginDataModules
import com.kkk.kopilot.datas.register.di.registerDataModules
import org.koin.dsl.module

val dataModules = module {
    includes(commonDataModules)
    includes(loginDataModules)
    includes(registerDataModules)
}