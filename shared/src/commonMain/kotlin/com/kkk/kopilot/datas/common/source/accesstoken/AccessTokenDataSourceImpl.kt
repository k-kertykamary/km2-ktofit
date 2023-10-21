package com.kkk.kopilot.datas.common.source.accesstoken

class AccessTokenDataSourceImpl: AccessTokenDataSource {
    private var accessToken: String? = null
    override fun get(): String? {
        return accessToken
    }

    override fun set(accessToken: String) {
        // Save in shared preferences if need
        this.accessToken = accessToken
    }
}