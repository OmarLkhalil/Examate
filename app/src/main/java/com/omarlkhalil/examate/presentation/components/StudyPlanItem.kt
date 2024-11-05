package com.omarlkhalil.examate.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.omarlkhalil.examate.presentation.theme.ExamateTheme
import ir.kaaveh.sdpcompose.sdp

@Composable
private fun ProgressStepItem(
    stepNumber: Int,
    title: String,
    progress: Float = 0f,
    isCompleted: Boolean,
    isLocked: Boolean,
    showLine: Boolean = true
) {
    val tintColor = if (!isCompleted) ExamateTheme.color.primary400 else ExamateTheme.color.primary200
    val backGroundColor =
        if (!isCompleted) ExamateTheme.color.secondary400 else ExamateTheme.color.primary200
    val textColor = if (!isCompleted) ExamateTheme.color.primary400 else ExamateTheme.color.contentSecondary
    val progress = if (isLocked) 1f else progress
    val trackColor = if (isCompleted) ExamateTheme.color.primary400 else ExamateTheme.color.primary200

    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.size(90.sdp),
                    contentAlignment = Center
                ) {
                    CustomCircularProgressIndicator(
                        progress = progress,
                        strokeWidth = 6.sdp,
                        trackColor = trackColor,
                        modifier = Modifier.size(85.sdp),
                        progressColor = tintColor,
                    )
                    Box(
                        modifier = Modifier
                            .size(45.sdp)
                            .clip(CircleShape)
                            .border(2.sdp, tintColor, CircleShape)
                            .background(backGroundColor),
                        contentAlignment = Center
                    ) {
                        Text(
                            text = stepNumber.toString(),
                            style = ExamateTheme.typography.bold32,
                            color = if (!isCompleted) ExamateTheme.color.primary400 else ExamateTheme.color.white
                        )
                    }
                    if (isLocked) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(ExamateTheme.color.primary200)
                                .padding(5.sdp)
                                .align(Alignment.BottomEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "Locked",
                                tint = ExamateTheme.color.white,
                                modifier = Modifier.size(20.sdp)
                            )
                        }
                    }
                }
                if (showLine) {
                    Box(
                        modifier = Modifier
                            .width(6.sdp)
                            .height(40.sdp)
                            .background(backGroundColor)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.sdp))
            Text(
                text = title,
                modifier = Modifier.padding(bottom = 30.sdp),
                style = ExamateTheme.typography.bold24,
                color = textColor
            )
        }
    }
}

@Composable
internal fun ProgressSteps() {
    val listItem = listOf(
        ProgressStepItemData(1, "Unite 1: what is examate", false, 1f, false),
        ProgressStepItemData(2, "Unite 2: what is TCF", false, 1f, false),
        ProgressStepItemData(3, "Writing Tasks", false, 0.7f, false),
        ProgressStepItemData(4, "Oral Task", true, 0f, true),
        ProgressStepItemData(5, "Listening Task", true, 0f, true),
        ProgressStepItemData(6, "Reading Task", true, 0f, true),
        ProgressStepItemData(7, "Speaking Task", true, 0f, true),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(ExamateTheme.color.background)
            .padding(top = 10.sdp, start =10.sdp, end = 10.sdp, bottom = 0.sdp),
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(listItem) { index, item ->
            ProgressStepItem(
                stepNumber = item.stepNumber,
                title = item.title,
                isCompleted = item.isCompleted,
                progress = item.progress,
                isLocked = item.isLocked,
                showLine = index != listItem.size - 1
            )
        }
    }

}


data class ProgressStepItemData(
    val stepNumber: Int,
    val title: String,
    val isCompleted: Boolean,
    val progress: Float,
    val isLocked: Boolean
)

@Composable
private fun CustomCircularProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    trackColor: Color = Color.Gray,
    progressColor: Color = Color.Blue,
    strokeWidth: Dp = 6.sdp,
) {
    Canvas(modifier = modifier) {
        val sweepAngle = 360 * progress

        drawCircle(
            color = trackColor,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )

        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}

