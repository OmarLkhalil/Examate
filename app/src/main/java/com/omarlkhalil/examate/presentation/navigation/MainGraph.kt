package com.omarlkhalil.examate.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.omarlkhalil.examate.presentation.screens.connect.ConnectScreen
import com.omarlkhalil.examate.presentation.screens.home.HomeScreen
import com.omarlkhalil.examate.presentation.screens.questions.QuestionScreenHintState
import com.omarlkhalil.examate.presentation.screens.questions.QuestionsScreen
import com.omarlkhalil.examate.presentation.screens.splash.SplashScreen


@Composable
internal fun MainGraph(navController: NavHostController, startDestination: String) {

    val graphModifier = Modifier
        .fillMaxSize()
        .imePadding()


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = graphModifier
    ) {
        homeScreen(navController)
        connectScreen(navController)
        questionsScreen(navController)
        profileScreen(navController)
        splashScreen(navController)
        toolsScreen(navController)
    }
}


private fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = Roots.Home.route) {
        HomeScreen()
    }
}

private fun NavGraphBuilder.connectScreen(navController: NavController) {
    composable(route = Roots.Connect.route) {
        ConnectScreen()
    }
}

private fun NavGraphBuilder.questionsScreen(navController: NavController) {
    composable(route = Roots.Questions.route) {
        QuestionsScreen(QuestionScreenHintState())
    }
}

private fun NavGraphBuilder.splashScreen(
    navController: NavController,
) {
    composable(route = Roots.Splash.route) {
        SplashScreen {
            navController.navigate(route = Roots.Home.route,
                navOptions = navOptions {
                    popUpTo(Roots.Splash.route) {
                        inclusive = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.profileScreen(navController: NavController) {
    composable(route = Roots.Profile.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Profile")
        }
    }
}

private fun NavGraphBuilder.toolsScreen(navController: NavController) {
    composable(route = Roots.Tools.route) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Tools")
        }
    }
}