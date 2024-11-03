package com.omarlkhalil.rt_task.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.omarlkhalil.rt_task.presentation.components.ProgressSteps
import com.omarlkhalil.rt_task.presentation.theme.RTTheme
import com.omarlkhalil.rt_task.R
import ir.kaaveh.sdpcompose.sdp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(RTTheme.color.background),
        verticalArrangement = Arrangement.spacedBy(15.sdp),
    ) {
        HelloTextTile("Omar Khalil")
        Text(
            text = stringResource(R.string.study_plan),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 12.sdp),
            style = RTTheme.typography.bold24,
            color = RTTheme.color.primary600,
        )
        ProgressSteps()
    }
}


@Composable
fun HelloTextTile(
    title: String,
    textStyle: TextStyle = RTTheme.typography.bold24,
    textColor: Color = RTTheme.color.primary600,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 12.sdp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.sdp)
    ){
        Text(
            text = stringResource(R.string.hi),
            textAlign = TextAlign.Start,
            style = RTTheme.typography.medium24,
            color = textColor
        )
        Text(
            text = title,
            textAlign = TextAlign.Start,
            style = textStyle,
            color = textColor
        )
    }

}