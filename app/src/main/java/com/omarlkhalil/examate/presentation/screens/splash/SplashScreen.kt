package com.omarlkhalil.examate.presentation.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.presentation.screens.elements.VSpacer
import com.omarlkhalil.examate.presentation.theme.RTTheme
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun SplashScreen(
    navigateOut: () -> Unit
) {

    var isOut by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(RTTheme.color.black.copy(alpha = 0.7f))
            .pointerInput(Unit) {
                detectTapGestures { isOut = true }
            }
    ) {

        LaunchedEffect(isOut) {
            if (!isOut) {
                delay((1.5).seconds)
                isOut = true
            } else {
                navigateOut()
            }
        }
        val modifier = remember {
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 14.dp)
        }
        VSpacer(height = 180.sdp)
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.splash_welcome),
            style = RTTheme.typography.medium18,
            color = RTTheme.color.white
        )
        VSpacer(height = 24.sdp)
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.splash_onboarding),
            style = RTTheme.typography.medium24,
            color = RTTheme.color.blue
        )
    }

}