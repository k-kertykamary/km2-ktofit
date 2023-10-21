package com.kkk.kopilot.datas.common.source.accesstoken

interface AccessTokenDataSource {
    fun get(): String?
    fun set(accessToken: String)
}