package com.omarlkhalil.examate.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.omarlkhalil.examate.presentation.extensions.noIndicationClickable
import com.omarlkhalil.examate.presentation.theme.RTTheme
import ir.kaaveh.sdpcompose.sdp


@Composable
internal fun RTBottomNavigationItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    icon: Int,
    text: Int,
    onClick: () -> Unit,
) {
    val tintColor = if (selected) RTTheme.color.primary400 else RTTheme.color.contentSecondary
    val textStyle = if (selected) RTTheme.typography.bold14 else RTTheme.typography.medium12
    val iconSize = if(selected) 22.sdp else 18.sdp

    Column(
        modifier = modifier.noIndicationClickable(onClick = onClick),
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            modifier = Modifier.size(iconSize),
            contentDescription = "Bottom Navigation Item Icon",
            tint = tintColor,
        )
        Spacer(modifier = Modifier.size(RTTheme.dimens.space4))
        Text(
            text = stringResource(text),
            style = textStyle,
            maxLines = 1,
            color = tintColor
        )
    }
}
