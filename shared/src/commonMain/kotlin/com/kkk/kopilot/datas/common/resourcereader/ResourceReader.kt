package com.kkk.kopilot.datas.common.resourcereader

expect class ResourceReader() {
    fun read(name: String): String
}