package com.omarlkhalil.examate.presentation.extensions

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role


private const val DefaultInitialScale = 1f
private const val DefaultTargetScale = .9f
private const val DefaultAnimationDuration = 50

fun Modifier.noIndicationClickable(
    interactionSource: MutableInteractionSource? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: ()->Unit
) = composed {
    clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}



fun Modifier.rippleClickable(
    interactionSource: MutableInteractionSource? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: ()->Unit
) = composed {
    clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = LocalIndication.current,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

fun Modifier.clippedRippleClickable(
    interactionSource: MutableInteractionSource? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    clipShape: Shape? = CircleShape,
    onClick: ()->Unit
) = composed {
    if (clipShape == null) { this } else { clip(clipShape) }.clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = LocalIndication.current,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}


fun Modifier.shake(
    enabled: Boolean,
    initialScale: Float = DefaultInitialScale,
    targetScale: Float = DefaultTargetScale,
    animation: DurationBasedAnimationSpec<Float> = tween(
        durationMillis = DefaultAnimationDuration,
        easing = LinearEasing,
    ),
) = composed {
    if (enabled) {
        val infiniteTransition = rememberInfiniteTransition()
        val scale by infiniteTransition.animateFloat(
            initialValue = initialScale,
            targetValue = targetScale,
            animationSpec = infiniteRepeatable(
                animation = animation,
                repeatMode = RepeatMode.Reverse,
            ),
            label = "",
        )
        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    } else {
        this
    }
}
