package com.kkk.kopilot.datas.common.resourcereader

actual class ResourceReader {
    actual fun read(name: String): String {
        throw NotImplementedError()
    }
}