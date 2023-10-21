package com.kkk.kopilot.presentation.component.loader.loadingindicator

import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope

@Stable
interface LoadingIndicatorState {
    operator fun get(index: Int): Float
    fun start(animationType: AnimationType, scope: CoroutineScope)
}