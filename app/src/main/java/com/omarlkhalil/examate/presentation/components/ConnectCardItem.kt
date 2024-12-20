package com.omarlkhalil.examate.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.omarlkhalil.examate.domain.model.connections.ConnectItemModel
import com.omarlkhalil.examate.presentation.theme.ExamateTheme
import ir.kaaveh.sdpcompose.sdp

@Composable
internal fun ConnectCardItem(
    itemModel: ConnectItemModel
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = ExamateTheme.color.white),
        shape = ExamateTheme.shapes.large,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.sdp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.sdp),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 7.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                ProfilePicture(
                    image = itemModel.image,
                    size = 70
                )
                BodyTitleItem(
                    name = itemModel.userName,
                    target = itemModel.target,
                    items = itemModel.languages
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 7.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                itemModel.userInfo.forEach {
                    ConnectTextItem(icon = it.icon!!, title = it.title)
                }
            }
        }
    }

}


@Composable
private fun ProfilePicture(
    modifier: Modifier = Modifier,
    image: Any? = null,
    size: Int,
    contentScale: ContentScale = ContentScale.Crop,
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = "Profile Picture",
        contentScale = contentScale,
        loading = {
            CircularProgressIndicator()
        },
        modifier = modifier
            .clip(CircleShape)
            .background(ExamateTheme.color.primary600)
            .size(size.dp)
    )

}

@Composable
private fun BodyTitleItem(
    name: String,
    target: String,
    items: List<String>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.sdp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Text(
                text = name,
                textAlign = TextAlign.Start,
                style = ExamateTheme.typography.bold18,
                color = ExamateTheme.color.primary600
            )
            Box(
                modifier = Modifier
                    .clip(ExamateTheme.shapes.medium)
                    .width(100.sdp)
                    .height(30.sdp)
                    .padding(2.sdp)
                    .background(ExamateTheme.color.primary600),
                contentAlignment = Center
            ) {
                Text(
                    text = "Targeting: $target",
                    style = ExamateTheme.typography.medium14,
                    color = ExamateTheme.color.white,
                )
            }
        }
        Text(
            text = "Last seen online: Yesterday",
            textAlign = TextAlign.Start,
            style = ExamateTheme.typography.medium14,
            color = ExamateTheme.color.primary200,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.sdp),
        ) {
            items.forEach {
                Box(
                    modifier = Modifier
                        .clip(ExamateTheme.shapes.medium)
                        .width(55.sdp)
                        .height(20.sdp)
                        .padding(2.sdp)
                        .background(ExamateTheme.color.secondary400),
                    contentAlignment = Center
                ) {
                    Text(
                        text = it,
                        style = ExamateTheme.typography.medium10,
                        color = ExamateTheme.color.primary600,
                    )
                }
            }
        }
    }
}

@Composable
private fun ConnectTextItem(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .padding(3.sdp)
            .height(30.sdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.sdp)
    ) {
        Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(16.sdp))
        Text(text = title, style = ExamateTheme.typography.medium12, color = ExamateTheme.color.primary200)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreivewItem() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BodyTitleItem(
            name = "Omar",
            target = "B2",
            items = listOf("English", "Arabic", "French")
        )
    }
}