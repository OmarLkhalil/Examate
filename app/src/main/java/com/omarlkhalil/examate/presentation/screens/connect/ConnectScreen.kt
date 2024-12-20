package com.omarlkhalil.examate.presentation.screens.connect

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.Lifecycle
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.domain.model.connections.ConnectItemModel
import com.omarlkhalil.examate.domain.model.connections.UserModel
import com.omarlkhalil.examate.presentation.components.ConnectCardItem
import com.omarlkhalil.examate.presentation.screens.elements.ComposableLifecycle
import com.omarlkhalil.examate.presentation.screens.elements.ScreenContainer
import com.omarlkhalil.examate.presentation.theme.ExamateTheme
import ir.kaaveh.sdpcompose.sdp


@Composable
fun ConnectScreen() = ScreenContainer {
    Tabs()
}

@Composable
fun Tabs() {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(R.string.suggestions), stringResource(R.string.chat))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = ExamateTheme.color.background,
            contentColor = ExamateTheme.color.primary400,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = ExamateTheme.color.primary400
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> Suggestions()
            1 -> Chat()
        }
    }
}


@Composable
fun Suggestions() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Suggested Study Partners",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    style = ExamateTheme.typography.bold18,
                    color = ExamateTheme.color.primary600
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filters),
                        contentDescription = "Home",
                        tint = ExamateTheme.color.primary400,
                        modifier = Modifier
                            .size(24.sdp)
                    )
                }
            }
        }
        items(connectionsList) {
            ConnectCardItem(it)
        }
    }
}

@Composable
fun Chat() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        items(connectionsList) {
            ConnectCardItem(it)
        }
    }
}


val connectionsList = listOf(
    ConnectItemModel(
        userName = "Omar Doe",
        image = "https://randomuser.me/api/portraits/men/74.jpg",
        target = "B2",
        userInfo = listOf(
            UserModel("Cairo", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("26", Icons.Outlined.DateRange),
            UserModel("12/12/2021", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English")
    ),
    ConnectItemModel(
        userName = "Ahmed Doe",
        image = "https://randomuser.me/api/portraits/men/65.jpg",
        target = "B2",
        userInfo = listOf(
            UserModel("Alexandria", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("30", Icons.Outlined.DateRange),
            UserModel("10/10/2020", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "French")
    ),
    ConnectItemModel(
        userName = "Sara Doe",
        image = "https://randomuser.me/api/portraits/women/68.jpg",
        target = "C1",
        userInfo = listOf(
            UserModel("Giza", Icons.Outlined.LocationOn),
            UserModel("Female", Icons.Outlined.Person),
            UserModel("24", Icons.Outlined.DateRange),
            UserModel("05/05/2019", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English", "Spanish")
    ),
    ConnectItemModel(
        userName = "John Doe",
        image = "https://randomuser.me/api/portraits/men/75.jpg",
        target = "B1",
        userInfo = listOf(
            UserModel("New York", Icons.Outlined.LocationOn),
            UserModel("Male", Icons.Outlined.Person),
            UserModel("35", Icons.Outlined.DateRange),
            UserModel("01/01/2020", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "Spanish")
    ),
    ConnectItemModel(
        userName = "Emily Doe",
        image = "https://randomuser.me/api/portraits/women/73.jpg",
        target = "C2",
        userInfo = listOf(
            UserModel("Florida", Icons.Outlined.LocationOn),
            UserModel("Female", Icons.Outlined.Person),
            UserModel("29", Icons.Outlined.DateRange),
            UserModel("02/02/2021", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "French")
    )
)
