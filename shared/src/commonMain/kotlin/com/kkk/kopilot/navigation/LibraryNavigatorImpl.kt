package com.kkk.kopilot.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LibraryNavigatorImpl: LibraryNavigator {
    private val _navigationEvent = MutableSharedFlow<NavigationIntent>(
        extraBufferCapacity = 10
    )
    override val navigationEvent: SharedFlow<NavigationIntent>
        get() = _navigationEvent.asSharedFlow()

    override fun tryNavigationIntent(navIntent: NavigationIntent) {
        _navigationEvent.tryEmit(navIntent)
    }
}