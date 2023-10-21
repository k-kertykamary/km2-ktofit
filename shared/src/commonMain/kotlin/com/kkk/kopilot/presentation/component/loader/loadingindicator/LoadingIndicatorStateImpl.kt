package com.kkk.kopilot.presentation.component.loader.loadingindicator

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadingIndicatorStateImpl: LoadingIndicatorState {
    private val animatedValues = List(NumIndicators) { mutableStateOf(0f) }

    override fun get(index: Int): Float = animatedValues[index].value

    override fun start(animationType: AnimationType, scope: CoroutineScope) {
        repeat(NumIndicators) { index ->
            scope.launch {
                animate(
                    initialValue = animationType.initialValue,
                    targetValue = animationType.targetValue,
                    animationSpec = infiniteRepeatable(
                        animation = animationType.animationSpec,
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset(animationType.animationDelay * index)
                    ),
                ) { value, _ -> animatedValues[index].value = value }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return (other as? LoadingIndicatorStateImpl)?.let {
            (animatedValues == it.animatedValues)
        }?: return false
    }

    override fun hashCode(): Int {
        return animatedValues.hashCode()
    }
}