package com.kkk.kopilot.datas.common.resourcereader

import java.io.InputStreamReader

actual class ResourceReader {
    actual fun read(name: String): String {
        return javaClass.classLoader?.getResourceAsStream(name)?.use { stream ->
            InputStreamReader(stream).use { reader ->
                reader.readText()
            }
        } ?: error("Couldn't load resource: $name")
    }
}