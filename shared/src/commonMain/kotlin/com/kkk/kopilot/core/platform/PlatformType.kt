package com.kkk.kopilot.core.platform

sealed class PlatformType {
    data object Android: PlatformType()
    data object IoS: PlatformType()
    data object Desktop: PlatformType()
}