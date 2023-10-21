package com.kkk.kopilot.navigation

import kotlinx.coroutines.flow.SharedFlow

interface LibraryNavigator {
    val navigationEvent: SharedFlow<NavigationIntent>
    fun tryNavigationIntent(navIntent: NavigationIntent)
}

enum class NavigationIntent(val value: String) {
    NavigationEnd("END"),
    NavigationUnknown("UNKNOWN"),
}