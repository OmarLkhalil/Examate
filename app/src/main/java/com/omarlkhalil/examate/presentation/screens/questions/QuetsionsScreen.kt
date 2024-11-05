package com.omarlkhalil.examate.presentation.screens.questions

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.domain.model.questions.OralQuestionsModel
import com.omarlkhalil.examate.domain.model.questions.WritingQuestionsModel
import com.omarlkhalil.examate.presentation.components.OralQuestionsCard
import com.omarlkhalil.examate.presentation.components.ToolTipHintItem
import com.omarlkhalil.examate.presentation.components.ToolTipItem
import com.omarlkhalil.examate.presentation.components.WritingQuestionsCard
import com.omarlkhalil.examate.presentation.screens.SharedViewModel
import com.omarlkhalil.examate.presentation.screens.elements.ScreenContainer
import com.omarlkhalil.examate.presentation.theme.RTTheme
import ir.kaaveh.sdpcompose.sdp


@Composable
internal fun QuestionsScreen(
    questionScreenHintState: QuestionScreenHintState,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val visibleHintCoordinates = remember {mutableStateOf(viewModel.visibleHintCoordinates.value)}
    ScreenContainer{
        QuestionsTabs(
            questionScreenHintState,
            visibleHintCoordinates,
            viewModel
        )
    }
}

@Composable
internal fun QuestionsTabs(
    questionScreenHintState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    viewModel: SharedViewModel
) {
    val tabIndex by viewModel.questionsTabsIndex.collectAsState()
    val tabs = listOf(stringResource(R.string.writing), stringResource(R.string.oral))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            modifier = Modifier.width(240.sdp),
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
                Tab(text = {
                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.sdp)
                    ) {
                        if (index == 0) {
                            Icon(
                                painter = painterResource(R.drawable.ic_writing),
                                modifier = Modifier.size(18.sdp),
                                tint = RTTheme.color.primary400,
                                contentDescription = ""
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.ic_oral),
                                modifier = Modifier.size(18.sdp),
                                tint = RTTheme.color.primary400,
                                contentDescription = ""
                            )
                        }
                        Text(
                            title,
                            style = RTTheme.typography.bold16,
                            color = RTTheme.color.primary400
                        )
                    }
                },
                    selected = tabIndex == index,
                    onClick = { viewModel.setTabsIndex(index) }
                )
            }
        }
        when (tabIndex) {
            0 -> Writing(
                questionScreenHintState,
                visibleHintCoordinates
            )

            1 -> Oral(
                questionScreenHintState,
                visibleHintCoordinates,
                viewModel
            )
        }
    }
}

@Composable
internal fun Oral(
    questionsState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    viewModel: SharedViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp)
    ) {
        item {
            if (questionsState.isFilterHintVisible.value) {
                ToolTipHintItem(
                    iconRes = R.drawable.ic_filters,
                    textRes = R.string.home,
                    isHintVisible = questionsState.isFilterHintVisible,
                    visibleHintCoordinates = visibleHintCoordinates,
                    onClick = {
                        viewModel.updateSelectedIndex(2)
                    },
                    customContent = {
                        Row(
                            modifier = Modifier
                                .clip(RTTheme.shapes.small)
                                .width(100.sdp)
                                .height(40.sdp)
                                .background(RTTheme.color.secondary400),
                            verticalAlignment = CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Filter",
                                textAlign = TextAlign.Start,
                                style = RTTheme.typography.bold16,
                                color = RTTheme.color.primary600
                            )
                            Icon(
                                painter = painterResource(R.drawable.ic_filters),
                                contentDescription = "Filter",
                                tint = RTTheme.color.primary400,
                                modifier = Modifier.size(20.sdp)
                            )
                        }
                    },
                    customHintContent = {
                        ToolTipItem(
                            hintText = stringResource(R.string.hint),
                            isHintVisible = questionsState.isFilterHintVisible,
                            onNextClick = {
                                questionsState.isFilterHintVisible.value =
                                    !questionsState.isFilterHintVisible.value
                                viewModel.updateSelectedIndex(3)
                                Handler(Looper.getMainLooper()).postDelayed({
                                    questionsState.isToolsHintVisible.value =
                                        !questionsState.isToolsHintVisible.value
                                }, 1000)
                            },
                            icon = R.drawable.ic_filters
                        )
                    }
                )
            } else {
                // Default display without the hint
                Row(
                    modifier = Modifier
                        .clip(RTTheme.shapes.small)
                        .width(100.sdp)
                        .height(40.sdp)
                        .background(RTTheme.color.secondary400),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Filter",
                        textAlign = TextAlign.Start,
                        style = RTTheme.typography.bold16,
                        color = RTTheme.color.primary600
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_filters),
                        contentDescription = "Filter",
                        tint = RTTheme.color.primary400,
                        modifier = Modifier.size(20.sdp)
                    )
                }
            }
        }
        items(questionsList) {

            OralQuestionsCard(it)

        }
    }
}

@Composable
private fun Writing(
    questionScreenHintState: QuestionScreenHintState,
    visibleCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.sdp)
    ) {
        CustomTabSample(questionScreenHintState, visibleCoordinates)
    }

}

@Composable
internal fun CustomTabSample(
    questionScreenHintState: QuestionScreenHintState,
    visibleCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
) {

    val (selected, setSelected) = remember {
        mutableIntStateOf(0)
    }

    CustomTab(
        items = listOf("Task 1", "Task 2"),
        selectedItemIndex = selected,
        modifier = Modifier.padding(horizontal = 10.sdp),
        onClick = setSelected,
    )

    when (selected) {
        0 -> QuestionsGridList(questionScreenHintState, visibleCoordinates)
        1 -> QuestionsGridList(questionScreenHintState, visibleCoordinates)
    }
}

@Composable
private fun QuestionsGridList(
    questionScreenHintState: QuestionScreenHintState,
    visibleHintCoordinates: MutableState<LayoutCoordinates?> = mutableStateOf(null),
    viewModel: SharedViewModel = hiltViewModel(),
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.sdp),
        contentPadding = PaddingValues(10.sdp),
        horizontalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        items(writingsList) {
            if (writingsList.indexOf(it) == 0) {
                ToolTipHintItem(
                    iconRes = R.drawable.ic_filters,
                    textRes = R.string.home,
                    isHintVisible = questionScreenHintState.isFirstItemHintVisible,
                    visibleHintCoordinates = visibleHintCoordinates,
                    onClick = {
                    },
                    customContent = {
                        WritingQuestionsCard(it)
                    },
                    customHintContent = {
                        ToolTipItem(
                            hintText = stringResource(R.string.hint),
                            isHintVisible = questionScreenHintState.isFirstItemHintVisible,
                            onNextClick = {
                                questionScreenHintState.isFirstItemHintVisible.value =
                                    !questionScreenHintState.isFirstItemHintVisible.value
                                Handler(Looper.getMainLooper()).postDelayed({
                                    viewModel.setTabsIndex(1)
                                    questionScreenHintState.isFilterHintVisible.value =
                                        !questionScreenHintState.isFilterHintVisible.value
                                }, 1000)
                            },
                            icon = R.drawable.ic_filters
                        )
                    }
                )
            } else {
                WritingQuestionsCard(it)
            }
        }
    }
}


@Composable
internal fun CustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    onClick: (index: Int) -> Unit,
) {
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing), label = "",
    )

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(RTTheme.color.secondary400)
            .height(intrinsicSize = IntrinsicSize.Min),
    ) {
        MyTabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = RTTheme.color.primary600,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clip(CircleShape),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                MyTabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabWidth,
                    text = text,
                )
            }
        }
    }
}

@Composable
private fun MyTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            White
        } else {
            Black
        },
        animationSpec = tween(easing = LinearEasing), label = "",
    )
    Text(
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .width(tabWidth)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp,
            ),
        text = text,
        color = tabTextColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun MyTabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = CircleShape,
            )
            .background(
                color = indicatorColor,
            ),
    )
}

val questionsList = listOf(
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Events", "Task 2"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Culture", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
    OralQuestionsModel(
        question = "Je suis votre collègue, je participe chaque année à une course à pieds pour célébrer le printemps.Vous êtes\n" +
                "intéressé. Vous me posez des questions pour avoir des informations (parcours, durée, participants, etc.)",
        answersCount = 11,
        answer = "Paris",
        titles = listOf("Technology", "Task 3"),
        date = "13 May 2023"
    ),
)

val writingsList = listOf(
    WritingQuestionsModel(
        type = "Voyage",
        answersCount = 10,
        questionsCount = 10,
        progress = 100f,
        icon = R.drawable.ic_travel
    ),
    WritingQuestionsModel(
        type = "Immigration",
        answersCount = 6,
        questionsCount = 10,
        progress = 50f,
        icon = R.drawable.ic_travel
    ),
    WritingQuestionsModel(
        type = "Technologie",
        answersCount = 4,
        questionsCount = 10,
        progress = 60f,
        icon = R.drawable.ic_tech
    ),
    WritingQuestionsModel(
        type = "Art et Culture",
        answersCount = 4,
        questionsCount = 10,
        progress = 70f,
        icon = R.drawable.ic_art
    ),
    WritingQuestionsModel(
        type = "Environm-ent ",
        answersCount = 5,
        questionsCount = 10,
        progress = 40f,
        icon = R.drawable.ic_enviroment
    ),
    WritingQuestionsModel(
        type = "Travel",
        answersCount = 3,
        questionsCount = 10,
        progress = 20f,
        icon = R.drawable.ic_travel
    )
)

