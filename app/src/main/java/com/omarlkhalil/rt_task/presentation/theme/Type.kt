package com.omarlkhalil.rt_task.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.omarlkhalil.rt_task.R


val rtTypography = RTTypography(
    bold14 = TextStyle(
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 14.sp
    ),
    bold16 = TextStyle(
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 16.sp
    ),
    bold18 = TextStyle(
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 18.sp
    ),
    bold24 = TextStyle(
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 24.sp
    ),
    bold32 = TextStyle(
        fontFamily = FontFamily(Font(R.font.bold)),
        fontSize = 32.sp
    ),
    medium10 = TextStyle(
        fontFamily = FontFamily(Font(R.font.medium)),
        fontSize = 10.sp
    ),
    medium12 = TextStyle(
        fontFamily = FontFamily(Font(R.font.medium)),
        fontSize = 12.sp
    ),
    medium14 = TextStyle(
        fontFamily = FontFamily(Font(R.font.medium)),
        fontSize = 14.sp
    ),
    medium24 = TextStyle(
        fontFamily = FontFamily(Font(R.font.medium)),
        fontSize = 24.sp
    ),
)


data class RTTypography(
    val bold14: TextStyle,
    val bold16: TextStyle,
    val bold18: TextStyle,
    val bold24: TextStyle,
    val bold32: TextStyle,
    val medium10: TextStyle,
    val medium12: TextStyle,
    val medium14: TextStyle,
    val medium24: TextStyle,
)