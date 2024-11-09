package com.omarlkhalil.examate.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val localColorScheme = staticCompositionLocalOf { examateThemeColors }
private val localDimens = staticCompositionLocalOf { Dimens() }
private val localTypography = staticCompositionLocalOf { examateTypography }
private val localShapes = staticCompositionLocalOf { shapes }


@Composable
fun ExamateTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        localColorScheme provides examateThemeColors,
        localDimens provides Dimens(),
        localTypography provides examateTypography,
        localShapes provides shapes
    ) {
        content()
    }
}

object ExamateTheme {

    val color: ExamateColor
        @Composable
        get() = localColorScheme.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current

    val typography: ExamateTypography
        @Composable
        @ReadOnlyComposable
        get() = localTypography.current

    val shapes: ExamateShapes
        @Composable
        @ReadOnlyComposable
        get() = localShapes.current
}
