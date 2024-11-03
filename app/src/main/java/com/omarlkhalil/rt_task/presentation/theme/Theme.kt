package com.omarlkhalil.rt_task.presentation.theme

import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val localColorScheme = staticCompositionLocalOf { rtThemeColors }
private val localDimens = staticCompositionLocalOf { Dimens() }
private val localTypography = staticCompositionLocalOf { rtTypography }
private val localShapes = staticCompositionLocalOf { shapes }


@Composable
fun RTScreenTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localColorScheme provides rtThemeColors,
        localDimens provides Dimens(),
        localTypography provides rtTypography,
        localShapes provides shapes
    ) {
        content()
    }
}

object RTTheme {

    val color: RTColor
        @Composable
        get() = localColorScheme.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current

    val typography: RTTypography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val shapes: RTShapes
        @Composable
        @ReadOnlyComposable
        get() = localShapes.current
}
