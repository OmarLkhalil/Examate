package com.omarlkhalil.examate.presentation.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.yml.tooltip.ui.ToolTipScreen
import co.yml.tooltip.ui.isTipVisible
import com.omarlkhalil.examate.R
import com.omarlkhalil.examate.presentation.components.BaseTopBar
import com.omarlkhalil.examate.presentation.components.RTBottomNavigationItem
import com.omarlkhalil.examate.presentation.components.ToolTipHintItem
import com.omarlkhalil.examate.presentation.components.ToolTipItem
import com.omarlkhalil.examate.presentation.extensions.currentScreenAsState
import com.omarlkhalil.examate.presentation.extensions.navigateToRootScreen
import com.omarlkhalil.examate.presentation.navigation.MainGraph
import com.omarlkhalil.examate.presentation.navigation.Roots
import com.omarlkhalil.examate.presentation.screens.connect.ConnectScreen
import com.omarlkhalil.examate.presentation.screens.elements.ScreenContainer
import com.omarlkhalil.examate.presentation.screens.home.HomeScreen
import com.omarlkhalil.examate.presentation.screens.questions.QuestionScreenHintState
import com.omarlkhalil.examate.presentation.screens.questions.QuestionsScreen
import com.omarlkhalil.examate.presentation.screens.splash.SplashScreen
import com.omarlkhalil.examate.presentation.theme.ExamateTheme

val bottomBarHeight = 90.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val currentScreen by navController.currentScreenAsState()

    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

    val bottomBarColor = ExamateTheme.color.white
    val currentRoute = currentScreen.route

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    val bottomNavModifier =
            Modifier
                .height(bottomBarHeight)
                .background(color = bottomBarColor)
                .fillMaxWidth()


    val isTutorialActive by sharedViewModel.isTutorialActive.collectAsState()
    val visibleHintCoordinates: MutableState<LayoutCoordinates?> = remember { mutableStateOf(null) }
    val isHintVisibleHome = remember { mutableStateOf(true) }
    val isHintVisibleConnect = remember { mutableStateOf(false) }
    val isHintVisibleQuestions = remember { mutableStateOf(false) }
    val isHintVisibleTools = remember { mutableStateOf(false) }
    val isFirstItemHintVisible = remember { mutableStateOf(false) }
    val isHintVisibleProfile = remember { mutableStateOf(false) }
    val isFilterHintVisible = remember { mutableStateOf(false) }
    val selectedIndex by sharedViewModel.selectedIndex.collectAsState()

    var isSplashFinished by remember { mutableStateOf(false) }
    if (isTutorialActive) {
        SplashScreen { isSplashFinished = true }
        if (isSplashFinished) {
            ToolTipScreen(
                paddingHighlightArea = 0f,
                backgroundTransparency = 0.7f,
                cornerRadiusHighlightArea = 0f,
                mainContent = {
                    Scaffold(
                        modifier = modifier.nestedScroll(nestedScrollConnection),
                        containerColor = ExamateTheme.color.background,
                        topBar = {
                            BaseTopBar(navController)
                        },
                        bottomBar = {
                            RTBottomNavigationToolTip(
                                modifier = Modifier
                                    .height(bottomBarHeight),
                                onUpdateSelectedIndex = {
                                    sharedViewModel.updateSelectedIndex(it)
                                },
                                mainHintState = MainHintState(
                                    visibleHintCoordinates = visibleHintCoordinates,
                                    isHintVisibleProfile = isHintVisibleProfile,
                                    isHintVisibleHome = isHintVisibleHome,
                                    isHintVisibleConnect = isHintVisibleConnect,
                                    isHintVisibleQuestions = isHintVisibleQuestions,
                                    isHintVisibleTools = isHintVisibleTools,
                                    isFirstItemHintVisible = isFirstItemHintVisible,
                                ),
                                viewModel = sharedViewModel
                            )
                        }, content = {
                            Box(Modifier.padding(it)) {
                                Body(
                                    selectedIndex,
                                    QuestionScreenHintState(
                                        isFilterHintVisible,
                                        isHintVisibleTools,
                                        isFirstItemHintVisible
                                    )
                                )
                            }
                        }
                    )
                },
                anyHintVisible = isTipVisible(
                    isHintVisibleHome,
                    isHintVisibleConnect,
                    isHintVisibleQuestions,
                    isHintVisibleTools,
                    isFirstItemHintVisible,
                    isFilterHintVisible,
                    isHintVisibleProfile
                ).value,
                visibleHintCoordinates = visibleHintCoordinates,
            )
        }
    } else {
        Scaffold(
            modifier = modifier.nestedScroll(nestedScrollConnection),
            containerColor = ExamateTheme.color.background,
            topBar = {
                BaseTopBar(navController)
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarOffsetHeightPx.floatValue != -bottomBarHeightPx,
                    enter = slideInVertically(
                        animationSpec = tween(1000)
                    ),
                    modifier = bottomNavModifier,
                    exit = slideOutVertically(
                        animationSpec = tween(10)
                    ),
                ) {
                    RTBottomNavigation(
                        navController = navController,
                        currentSelectedScreen = currentScreen,
                        containerColor = bottomBarColor
                    )
                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                MainGraph(navController = navController, startDestination = Roots.Home.route)
            }
        }
    }
}

@Composable
fun Body(
    selectedIndex: Int,
    questionScreenHintState: QuestionScreenHintState
) {
    when (selectedIndex) {
        0 -> {
            HomeScreen()
        }

        1 -> {
            ConnectScreen()
        }

        2 -> {
            QuestionsScreen(questionScreenHintState)
        }

        3 -> {
            ScreenContainer {
                Text(
                    text = "Tools",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }

        4 -> {
            ScreenContainer {
                Text(
                    text = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
private fun RTBottomNavigation(
    navController: NavController,
    currentSelectedScreen: Roots,
    modifier: Modifier = Modifier,
    containerColor: Color = ExamateTheme.color.white,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp,
    ) {
        RTBottomNavigationItem(
            selected = currentSelectedScreen == Roots.Home,
            onClick = { navController.navigateToRootScreen(Roots.Home) },
            icon = R.drawable.ic_nav_home,
            text = R.string.home,
            modifier = Modifier.weight(1f)
        )
        RTBottomNavigationItem(
            selected = currentSelectedScreen == Roots.Connect,
            onClick = { navController.navigateToRootScreen(Roots.Connect) },
            icon = R.drawable.ic_nav_connecters,
            text = R.string.connect,
            modifier = Modifier.weight(1f)
        )
        RTBottomNavigationItem(
            selected = currentSelectedScreen == Roots.Questions,
            onClick = { navController.navigateToRootScreen(Roots.Questions) },
            icon = R.drawable.ic_nav_questions,
            text = R.string.questions,
            modifier = Modifier.weight(1f)
        )
        RTBottomNavigationItem(
            selected = currentSelectedScreen == Roots.Tools,
            onClick = { navController.navigateToRootScreen(Roots.Tools) },
            icon = R.drawable.ic_nav_tools,
            text = R.string.tools,
            modifier = Modifier.weight(1f)
        )
        RTBottomNavigationItem(
            selected = currentSelectedScreen == Roots.Profile,
            onClick = { navController.navigateToRootScreen(Roots.Profile) },
            icon = R.drawable.ic_nav_profile,
            text = R.string.profile,
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
private fun RTBottomNavigationToolTip(
    modifier: Modifier = Modifier,
    containerColor: Color = ExamateTheme.color.white,
    onUpdateSelectedIndex: (Int) -> Unit,
    mainHintState: MainHintState,
    viewModel: SharedViewModel,
) {

    fun resetHints() {
        mainHintState.isHintVisibleHome.value = false
        mainHintState.isHintVisibleConnect.value = false
        mainHintState.isHintVisibleQuestions.value = false
        mainHintState.isHintVisibleTools.value = false
        mainHintState.isHintVisibleProfile.value = false
        mainHintState.isFirstItemHintVisible.value = false
    }

    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp
    ) {
        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_home,
            textRes = R.string.home,
            isHintVisible = mainHintState.isHintVisibleHome,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleHome.value = true
                onUpdateSelectedIndex(0)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici votre plan d'étude",
                    isHintVisible = mainHintState.isHintVisibleHome,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            mainHintState.isHintVisibleConnect.value = true
                        }, 1000)
                        onUpdateSelectedIndex(1)
                    },
                    icon = R.drawable.ic_nav_home
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_connecters,
            textRes = R.string.connect,
            isHintVisible = mainHintState.isHintVisibleConnect,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleConnect.value = true
                onUpdateSelectedIndex(1)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici des partenaires d'étude et des personnes avec qui vous connecter",
                    isHintVisible = mainHintState.isHintVisibleConnect,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            mainHintState.isHintVisibleQuestions.value = true
                        }, 1000)
                        onUpdateSelectedIndex(2)
                    },
                    icon = R.drawable.ic_nav_connecters
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_questions,
            textRes = R.string.questions,
            isHintVisible = mainHintState.isHintVisibleQuestions,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                viewModel.setTabsIndex(0)
                mainHintState.isHintVisibleQuestions.value = true
                onUpdateSelectedIndex(2)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Voici les questions avec des réponses modèles",
                    isHintVisible = mainHintState.isHintVisibleQuestions,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).post {
                            viewModel.setTabsIndex(0)
                            onUpdateSelectedIndex(2)
                            mainHintState.isFirstItemHintVisible.value = true
                        }
                    },
                    icon = R.drawable.ic_nav_questions
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_tools,
            textRes = R.string.tools,
            isHintVisible = mainHintState.isHintVisibleTools,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleTools.value = true
                onUpdateSelectedIndex(3)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = mainHintState.isHintVisibleTools,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            mainHintState.isHintVisibleProfile.value = true
                        }, 1000)
                        onUpdateSelectedIndex(4)
                    },
                    icon = R.drawable.ic_nav_tools
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_profile,
            textRes = R.string.profile,
            isHintVisible = mainHintState.isHintVisibleProfile,
            visibleHintCoordinates = mainHintState.visibleHintCoordinates,
            onClick = {
                resetHints()
                mainHintState.isHintVisibleProfile.value = true
                onUpdateSelectedIndex(4)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = mainHintState.isHintVisibleProfile,
                    onCloseClick = { viewModel.endTutorial() },
                    icon = R.drawable.ic_nav_profile
                )
            }
        )
    }
}
