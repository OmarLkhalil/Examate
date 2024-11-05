package com.omarlkhalil.examate.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.domain.model.questions.OralQuestionsModel
import com.omarlkhalil.examate.domain.model.questions.WritingQuestionsModel
import com.omarlkhalil.examate.presentation.theme.RTTheme
import ir.kaaveh.sdpcompose.sdp


@Composable
internal fun OralQuestionsCard(
    questionItem: OralQuestionsModel
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = RTTheme.color.white),
        shape = RTTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.sdp),
            verticalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OralQuestionsTextItem(modifier = Modifier.weight(1f), questionItem.titles)
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = "Home",
                        tint = RTTheme.color.black,
                        modifier = Modifier
                            .size(24.sdp)
                    )
                }
            }
            Text(
                text = questionItem.question,
                style = RTTheme.typography.medium14,
                color = RTTheme.color.contentPrimary
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(3.sdp)
                        .height(30.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.sdp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_answers),
                        contentDescription = "",
                        modifier = Modifier.size(16.sdp)
                    )
                    Text(
                        text = "${questionItem.answersCount} answers",
                        style = RTTheme.typography.medium12,
                        color = RTTheme.color.primary200
                    )
                }
                Text(
                    text = questionItem.date,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.sdp),
                    style = RTTheme.typography.medium12,
                    color = RTTheme.color.primary200
                )
            }
        }
    }
}


@Composable
internal fun WritingQuestionsCard(
    questionItem: WritingQuestionsModel
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = RTTheme.color.white),
        shape = RTTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.sdp, top = 7.sdp, bottom = 7.sdp, end = 25.sdp),
            verticalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Surface(
                color = RTTheme.color.secondary400,
                shape = RTTheme.shapes.small,
                modifier = Modifier
                    .height(15.sdp)
            ) {
                Text(
                    text = "${questionItem.answersCount} sur ${questionItem.questionsCount} Questions",
                    style = RTTheme.typography.medium12,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = RTTheme.color.primary600,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.sdp)
            ) {
                Icon(
                    painter = painterResource(questionItem.icon),
                    contentDescription = "Home",
                    tint = RTTheme.color.black,
                    modifier = Modifier
                        .size(18.sdp)
                )
                Text(
                    text = questionItem.type,
                    style = RTTheme.typography.bold16,
                    textAlign = TextAlign.Start,
                    color = RTTheme.color.primary200,
                )
            }
            Text(
                text = "Progress ${questionItem.progress.toInt()} %",
                style = RTTheme.typography.medium12,
                textAlign = TextAlign.Center,
                color = RTTheme.color.primary200,
            )
            CustomLinearIndicator(
                progress = questionItem.progress,
                modifier = Modifier.fillMaxWidth().padding(7.sdp),
            )
        }
    }
}

@Composable
private fun CustomLinearIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = RTTheme.color.secondary200,
    progressColor: Color = RTTheme.color.primary400,
    strokeWidth: Dp = 6.sdp,
) {
    val normalizedProgress = (progress.coerceIn(0f, 100f)) / 100f

    Canvas(modifier = modifier.height(strokeWidth)) {
        val width = size.width
        val height = size.height

        drawLine(
            color = trackColor,
            start = Offset(0f, height / 2),
            end = Offset(width, height / 2),
            strokeWidth = height,
            cap = StrokeCap.Round
        )

        drawLine(
            color = progressColor,
            start = Offset(0f, height / 2),
            end = Offset(width * normalizedProgress, height / 2),
            strokeWidth = height,
            cap = StrokeCap.Round
        )
    }
}



@Composable
private fun OralQuestionsTextItem(modifier: Modifier = Modifier, titles: List<String>) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.sdp),
    ) {
        titles.forEach {
            Surface(
                color = RTTheme.color.background,
                shape = RTTheme.shapes.small,
                modifier = Modifier
                    .width(60.sdp)
                    .height(15.sdp)
            ) {
                Text(
                    text = it,
                    style = RTTheme.typography.medium12,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    color = RTTheme.color.contentPrimary,
                )
            }
        }
    }
}


