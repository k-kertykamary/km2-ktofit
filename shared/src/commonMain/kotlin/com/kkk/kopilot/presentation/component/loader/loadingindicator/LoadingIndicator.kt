package com.kkk.kopilot.presentation.component.loader.loadingindicator

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


enum class LoadingIndicatorSize(val value: Int) {
    Small(8),
    Medium(12),
    Large(16),
}

enum class AnimationType {
    Bounce,
    LazyBounce,
    Fade,
}

val AnimationType.animationSpec: DurationBasedAnimationSpec<Float>
    get() = when (this) {
        AnimationType.Bounce,
        AnimationType.Fade -> tween(durationMillis = animationDuration)
        AnimationType.LazyBounce -> keyframes {
            durationMillis = animationDuration
            initialValue at 0
            0f at animationDuration / 4
            targetValue / 2f at animationDuration / 2
            targetValue / 2f at animationDuration
        }
    }

private val AnimationType.animationDuration: Int
    get() = when (this) {
        AnimationType.Bounce,
        AnimationType.LazyBounce -> BounceAnimationDurationMillis
        AnimationType.Fade -> FadeAnimationDurationMillis
    }

val AnimationType.animationDelay: Int
    get() = animationDuration / NumIndicators

val AnimationType.initialValue: Float
    get() = when (this) {
        AnimationType.Bounce -> IndicatorSize / 2f
        AnimationType.LazyBounce -> -IndicatorSize / 2f
        AnimationType.Fade -> 1f
    }

val AnimationType.targetValue: Float
    get() = when (this) {
        AnimationType.Bounce -> -IndicatorSize / 2f
        AnimationType.LazyBounce -> IndicatorSize / 2f
        AnimationType.Fade -> .2f
    }

const val NumIndicators = 3
var IndicatorSize = LoadingIndicatorSize.Medium.value
const val BounceAnimationDurationMillis = 300
const val FadeAnimationDurationMillis = 600
val MarginHalf = 2.dp

@Composable
fun LoadingIndicator(
    animating: Boolean = true,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    indicatorSpacing: Dp = MarginHalf,
    animationType: AnimationType = AnimationType.Bounce,
    size: LoadingIndicatorSize = LoadingIndicatorSize.Medium,
) {
    IndicatorSize = size.value
    val state = rememberLoadingIndicatorState(animating, animationType)
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(NumIndicators) { index ->
            LoadingDot(
                modifier = Modifier
                    .padding(horizontal = indicatorSpacing)
                    .width(IndicatorSize.dp)
                    .aspectRatio(1f)
                    .then(
                        when (animationType) {
                            AnimationType.Bounce,
                            AnimationType.LazyBounce -> Modifier.offset(
                                y = state[index].coerceAtMost(
                                    IndicatorSize / 2f
                                ).dp
                            )
                            AnimationType.Fade -> Modifier.graphicsLayer { alpha = state[index] }
                        }
                    ),
                color = color,
            )
        }
    }
}

@Composable
private fun LoadingDot(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Composable
fun rememberLoadingIndicatorState(
    animating: Boolean,
    animationType: AnimationType
): LoadingIndicatorState {
    val state = remember {
        LoadingIndicatorStateImpl()
    }
    LaunchedEffect(key1 = animating) {
        if (animating) {
            state.start(animationType, this)
        }
    }
    return state
}
