package com.omarlkhalil.examate.presentation.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role


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
