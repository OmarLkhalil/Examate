package com.omarlkhalil.examate.presentation.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
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
import com.omarlkhalil.examate.presentation.navigation.Roots
import com.omarlkhalil.examate.presentation.screens.SharedViewModel.Companion.ORAL_TAB_INDEX
import com.omarlkhalil.examate.presentation.screens.connect.ConnectScreen
import com.omarlkhalil.examate.presentation.screens.home.HomeScreen
import com.omarlkhalil.examate.presentation.screens.questions.QuestionScreenHintState
import com.omarlkhalil.examate.presentation.screens.questions.QuestionsScreen
import com.omarlkhalil.examate.presentation.theme.RTTheme
import kotlin.math.roundToInt

val bottomBarHeight = 80.dp


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

    val bottomBarColor = RTTheme.color.white
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
        if (currentRoute == Roots.Home.route) {
            Modifier
                .height(bottomBarHeight)
                .background(color = bottomBarColor)
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = 0,
                        y = -bottomBarOffsetHeightPx.floatValue.roundToInt()
                    )
                }
        } else {
            Modifier
                .height(bottomBarHeight)
                .background(color = bottomBarColor)
                .fillMaxWidth()
        }

    val isTutorialActive by sharedViewModel.isTutorialActive.collectAsState()
    val currentStep by sharedViewModel.currentTutorialStep.collectAsState()

    val visibleHintCoordinates: MutableState<LayoutCoordinates?> = remember { mutableStateOf(null) }
    val isHintVisibleHome = remember { mutableStateOf(true) }
    val isHintVisibleConnect = remember { mutableStateOf(false) }
    val isHintVisibleQuestions = remember { mutableStateOf(false) }
    val isHintVisibleTools = remember { mutableStateOf(false) }
    val isFirstItemHintVisible = remember { mutableStateOf(false) }
    val isHintVisibleProfile = remember { mutableStateOf(false) }
    val isFilterHintVisible = remember { mutableStateOf(false) }
    val selectedIndex by sharedViewModel.selectedIndex.collectAsState()


//    if (isTutorialActive) {
    ToolTipScreen(
        paddingHighlightArea = 0f,
        backgroundTransparency = 0.7f,
        cornerRadiusHighlightArea = 0f,
        mainContent = {
            Scaffold(
                modifier = modifier.nestedScroll(nestedScrollConnection),
                containerColor = RTTheme.color.background,
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
                        visibleHintCoordinates = visibleHintCoordinates,
                        isHintVisibleHome = isHintVisibleHome,
                        isHintVisibleConnect = isHintVisibleConnect,
                        isHintVisibleQuestions = isHintVisibleQuestions,
                        isHintVisibleTools = isHintVisibleTools,
                        isFirstItemHintVisible = isFirstItemHintVisible,
                        isHintVisibleProfile = isHintVisibleProfile,
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
//    } else {
//        Scaffold(
//            modifier = modifier.nestedScroll(nestedScrollConnection),
//            containerColor = RTTheme.color.background,
//            topBar = {
//                BaseTopBar(navController)
//            },
//            bottomBar = {
//                AnimatedVisibility(
//                    visible = bottomBarOffsetHeightPx.floatValue != -bottomBarHeightPx,
//                    enter = slideInVertically(
//                        animationSpec = tween(1000)
//                    ),
//                    modifier = bottomNavModifier,
//                    exit = slideOutVertically(
//                        animationSpec = tween(10)
//                    ),
//                ) {
//                    RTBottomNavigation(
//                        navController = navController,
//                        currentSelectedScreen = currentScreen,
//                        modifier = Modifier.fillMaxWidth(),
//                        containerColor = RTTheme.color.white
//                    )
//                }
//            }
//        ) {
//            Box(modifier = Modifier.padding(it)) {
//                MainGraph(navController = navController)
//            }
//        }
//    }
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Tools")
            }
        }

        4 -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Profile")
            }
        }
    }
}


@Composable
private fun RTBottomNavigation(
    navController: NavController,
    currentSelectedScreen: Roots,
    modifier: Modifier = Modifier,
    containerColor: Color = RTTheme.color.white,
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
    containerColor: Color = RTTheme.color.white,
    onUpdateSelectedIndex: (Int) -> Unit,
    visibleHintCoordinates: MutableState<LayoutCoordinates?>,
    isHintVisibleHome: MutableState<Boolean>,
    isHintVisibleConnect: MutableState<Boolean>,
    isHintVisibleQuestions: MutableState<Boolean>,
    isHintVisibleTools: MutableState<Boolean>,
    isHintVisibleProfile: MutableState<Boolean>,
    isFirstItemHintVisible: MutableState<Boolean>,
    viewModel: SharedViewModel,
) {

    fun resetHints() {
        isHintVisibleHome.value = false
        isHintVisibleConnect.value = false
        isHintVisibleQuestions.value = false
        isHintVisibleTools.value = false
        isHintVisibleProfile.value = false
        isFirstItemHintVisible.value = false
    }

    NavigationBar(
        modifier = modifier,
        containerColor = containerColor,
        tonalElevation = 0.dp
    ) {
        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_home,
            textRes = R.string.home,
            isHintVisible = isHintVisibleHome,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = {
                resetHints()
                isHintVisibleHome.value = true
                onUpdateSelectedIndex(0)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici votre plan d'étude",
                    isHintVisible = isHintVisibleHome,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            isHintVisibleConnect.value = true
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
            isHintVisible = isHintVisibleConnect,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = {
                resetHints()
                isHintVisibleConnect.value = true
                onUpdateSelectedIndex(1)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Vous trouverez ici des partenaires d'étude et des personnes avec qui vous connecter",
                    isHintVisible = isHintVisibleConnect,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            isHintVisibleQuestions.value = true
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
            isHintVisible = isHintVisibleQuestions,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = {
                resetHints()
                viewModel.setTabsIndex(0)
                isHintVisibleQuestions.value = true
                onUpdateSelectedIndex(2)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = "Voici les questions avec des réponses modèles",
                    isHintVisible = isHintVisibleQuestions,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).post {
                            viewModel.setTabsIndex(0)
                            onUpdateSelectedIndex(2)
                            isFirstItemHintVisible.value = true
                        }
                    },
                    icon = R.drawable.ic_nav_questions
                )
            }
        )

        ToolTipHintItem(
            iconRes = R.drawable.ic_nav_tools,
            textRes = R.string.tools,
            isHintVisible = isHintVisibleTools,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = {
                resetHints()
                isHintVisibleTools.value = true
                onUpdateSelectedIndex(3)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = isHintVisibleTools,
                    onCloseClick = { viewModel.endTutorial() },
                    onNextClick = {
                        resetHints()
                        Handler(Looper.getMainLooper()).postDelayed({
                            isHintVisibleProfile.value = true
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
            isHintVisible = isHintVisibleProfile,
            visibleHintCoordinates = visibleHintCoordinates,
            onClick = {
                resetHints()
                isHintVisibleProfile.value = true
                onUpdateSelectedIndex(4)
            },
            customHintContent = {
                ToolTipItem(
                    hintText = stringResource(R.string.hint),
                    isHintVisible = isHintVisibleProfile,
                    onCloseClick = { viewModel.endTutorial() },
                    icon = R.drawable.ic_nav_profile
                )
            }
        )
    }
}
