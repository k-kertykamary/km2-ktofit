package com.kkk.kopilot.datas.common.di

import com.kkk.kopilot.datas.common.source.accesstoken.AccessTokenDataSource
import com.kkk.kopilot.datas.common.source.accesstoken.AccessTokenDataSourceImpl
import org.koin.dsl.module

val commonDataModules = module {

    single <AccessTokenDataSource> {
        AccessTokenDataSourceImpl()
    }
}