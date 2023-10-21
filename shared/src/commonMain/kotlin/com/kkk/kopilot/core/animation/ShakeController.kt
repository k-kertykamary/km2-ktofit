package com.kkk.kopilot.core.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun rememberShakeController(): ShakeController {
    return remember { ShakeController() }
}

class ShakeController {
    var config: ShakeConfig? by mutableStateOf(null)
        private set

    fun shake(shakeConfig: ShakeConfig) {
        this.config = shakeConfig
    }
}

data class ShakeConfig(
    val iterations: Int = Int.MIN_VALUE,
    val intensity: Float = 100_000f,
    val rotate: Float = 0f,
    val rotateX: Float = 0f,
    val rotateY: Float = 0f,
    val scaleX: Float = 0f,
    val scaleY: Float = 0f,
    val translateX: Float = 25f,
    val translateY: Float = 0f
)


fun Modifier.shake(shakeController: ShakeController) = composed {
    shakeController.config?.let { shakeConfig ->
        val shake = remember { Animatable(0f) }
        LaunchedEffect(shakeController.config) {
            val maxIterations = if (shakeConfig.iterations > 0) shakeConfig.iterations else Int.MAX_VALUE
            for (i in 0 until maxIterations) {
                when (i % 2) {
                    0 -> shake.animateTo(1f, spring(stiffness = shakeConfig.intensity))
                    else -> shake.animateTo(-1f, spring(stiffness = shakeConfig.intensity))
                }
            }
        }

        this
            .rotate(shake.value * shakeConfig.rotate)
            .graphicsLayer {
                rotationX = shake.value * shakeConfig.rotateX
                rotationY = shake.value * shakeConfig.rotateY
            }
            .scale(
                scaleX = 1f + (shake.value * shakeConfig.scaleX),
                scaleY = 1f + (shake.value * shakeConfig.scaleY),
            )
            .offset {
                IntOffset(
                    (shake.value * shakeConfig.translateX).roundToInt(),
                    (shake.value * shakeConfig.translateY).roundToInt(),
                )
            }
    } ?: this
}