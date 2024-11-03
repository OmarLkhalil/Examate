package com.omarlkhalil.rt_task.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.omarlkhalil.rt_task.R
import com.omarlkhalil.rt_task.presentation.components.BaseTopBar
import com.omarlkhalil.rt_task.presentation.components.RTBottomNavigationItem
import com.omarlkhalil.rt_task.presentation.extensions.currentScreenAsState
import com.omarlkhalil.rt_task.presentation.extensions.navigateToRootScreen
import com.omarlkhalil.rt_task.presentation.navigation.MainGraph
import com.omarlkhalil.rt_task.presentation.navigation.Roots
import com.omarlkhalil.rt_task.presentation.theme.RTTheme
import kotlin.math.roundToInt


val bottomBarHeight = 80.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
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


    Scaffold(
        modifier = modifier.nestedScroll(nestedScrollConnection),
        containerColor = RTTheme.color.background,
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
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = RTTheme.color.white
                )
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MainGraph(navController = navController)
        }
    }
}

@Composable
private fun RTBottomNavigation(
    navController: NavController,
    currentSelectedScreen: Roots,
    modifier: Modifier = Modifier,
    containerColor: Color = RTTheme.color.white
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

