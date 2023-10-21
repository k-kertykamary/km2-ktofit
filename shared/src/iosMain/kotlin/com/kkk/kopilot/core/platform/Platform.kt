package com.kkk.kopilot.core.platform

actual class Platform {
    actual companion object {
        actual val current: PlatformType
            get() = PlatformType.IoS
    }
}