package com.omarlkhalil.examate.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.layout.LayoutCoordinates

data class MainHintState(
    val visibleHintCoordinates: MutableState<LayoutCoordinates?>  = mutableStateOf(null),
    val isHintVisibleHome: MutableState<Boolean> = mutableStateOf(false),
    val isHintVisibleConnect: MutableState<Boolean> = mutableStateOf(false),
    val isHintVisibleQuestions: MutableState<Boolean> = mutableStateOf(false),
    val isHintVisibleTools: MutableState<Boolean> = mutableStateOf(false),
    val isHintVisibleProfile: MutableState<Boolean> = mutableStateOf(false),
    val isFirstItemHintVisible: MutableState<Boolean> = mutableStateOf(false),
)