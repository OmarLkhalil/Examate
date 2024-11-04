package com.omarlkhalil.examate.presentation.screens.connect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.presentation.components.ConnectCardItem
import com.omarlkhalil.examate.presentation.components.ConnectItemModel
import com.omarlkhalil.examate.presentation.components.UserinfoItems
import com.omarlkhalil.examate.presentation.theme.RTTheme
import ir.kaaveh.sdpcompose.sdp


@Composable
fun ConnectScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Tabs()
    }
}

@Composable
fun Tabs() {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(R.string.suggestions), stringResource(R.string.chat))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = RTTheme.color.background,
            contentColor = RTTheme.color.primary400,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    color = RTTheme.color.primary400
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
        item{
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "Suggested Study Partners",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f),
                    style = RTTheme.typography.bold18,
                    color = RTTheme.color.primary600
                )
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filters),
                        contentDescription = "Home",
                        tint = RTTheme.color.primary400,
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
            UserinfoItems("Cairo", Icons.Outlined.LocationOn),
            UserinfoItems("Male", Icons.Outlined.Person),
            UserinfoItems("26", Icons.Outlined.DateRange),
            UserinfoItems("12/12/2021", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English")
    ),
    ConnectItemModel(
        userName = "Ahmed Doe",
        image = "https://randomuser.me/api/portraits/men/65.jpg",
        target = "B2",
        userInfo = listOf(
            UserinfoItems("Alexandria", Icons.Outlined.LocationOn),
            UserinfoItems("Male", Icons.Outlined.Person),
            UserinfoItems("30", Icons.Outlined.DateRange),
            UserinfoItems("10/10/2020", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "French")
    ),
    ConnectItemModel(
        userName = "Sara Doe",
        image = "https://randomuser.me/api/portraits/women/68.jpg",
        target = "C1",
        userInfo = listOf(
            UserinfoItems("Giza", Icons.Outlined.LocationOn),
            UserinfoItems("Female", Icons.Outlined.Person),
            UserinfoItems("24", Icons.Outlined.DateRange),
            UserinfoItems("05/05/2019", Icons.Outlined.DateRange)
        ),
        languages = listOf("Arabic", "English", "Spanish")
    ),
    ConnectItemModel(
        userName = "John Doe",
        image = "https://randomuser.me/api/portraits/men/75.jpg",
        target = "B1",
        userInfo = listOf(
            UserinfoItems("New York", Icons.Outlined.LocationOn),
            UserinfoItems("Male", Icons.Outlined.Person),
            UserinfoItems("35", Icons.Outlined.DateRange),
            UserinfoItems("01/01/2020", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "Spanish")
    ),
    ConnectItemModel(
        userName = "Emily Doe",
        image = "https://randomuser.me/api/portraits/women/73.jpg",
        target = "C2",
        userInfo = listOf(
            UserinfoItems("Florida", Icons.Outlined.LocationOn),
            UserinfoItems("Female", Icons.Outlined.Person),
            UserinfoItems("29", Icons.Outlined.DateRange),
            UserinfoItems("02/02/2021", Icons.Outlined.DateRange)
        ),
        languages = listOf("English", "French")
    )
)
