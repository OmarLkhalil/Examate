package com.omarlkhalil.examate.presentation.theme

import androidx.compose.ui.graphics.Color


data class RTColor(
    val primary800: Color,
    val primary600: Color,
    val primary400: Color,
    val primary200: Color,
    val secondary800: Color,
    val secondary600: Color,
    val secondary400: Color,
    val secondary200: Color,
    val surface: Color,
    val background: Color,
    val contentPrimary: Color,
    val contentSecondary: Color,
    val accent: Color,
    val black: Color = Color.Black,
    val gray: Color = Color.Gray,
    val white: Color = Color.White
)

val rtThemeColors = RTColor(
    primary800 = Color(0xFF0F5252), // Deep teal
    primary600 = Color(0xFF146D6D), // Dark teal
    primary400 = Color(0xFF21B6B6), // Light teal
    primary200 = Color(0xFF9CA3AF), // Soft blue-gray

    secondary800 = Color(0xFF1F2937), // Very dark gray-blue
    secondary600 = Color(0xFF188888), // Dark teal-blue
    secondary400 = Color(0xFFDDFBFB), // Light cyan
    secondary200 = Color(0xFFE5E7EB), // Light gray

    surface = Color(0xFFFFFFFF), // White
    background = Color(0xFFFDFEFF), // Off-white
    contentPrimary = Color(0xFF000000), // Black
    contentSecondary = Color(0xFF6B7280), // Medium gray
    accent = Color(0xFFF9FAFB) // Very light gray
)
