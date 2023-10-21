package com.kkk.kopilot.core.network.api.models

interface Response<T> {
    val isSuccessful: Boolean
    val code: Int
    val description: String

    fun body(): T
    fun headers(): Set<Map.Entry<String, List<String>>>
}