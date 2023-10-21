package com.kkk.kopilot.core.network.httpclient

import com.kkk.kopilot.core.platform.Platform
import com.kkk.kopilot.core.platform.PlatformType

object HttpConstants {
    val BASE_API_URL: String =
        if (Platform.current == PlatformType.IoS) "http://localhost:8080/" else "http://10.0.2.2:8080/"
}