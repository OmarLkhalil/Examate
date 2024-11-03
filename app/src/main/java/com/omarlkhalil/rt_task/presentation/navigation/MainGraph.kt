package com.omarlkhalil.rt_task.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omarlkhalil.rt_task.presentation.screens.home.HomeScreen


@Composable
fun MainGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Roots.Home.route) {
        homeScreen(navController)
        connectScreen(navController)
        questionsScreen(navController)
        profileScreen(navController)
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
        Text(text = "Connect")
    }
}

private fun NavGraphBuilder.questionsScreen(navController: NavController) {
    composable(route = Roots.Questions.route) {
        Text(text = "Questions")
    }
}

private fun NavGraphBuilder.profileScreen(navController: NavController) {
    composable(route = Roots.Profile.route) {
        Text(text = "Profile")
    }
}

private fun NavGraphBuilder.toolsScreen(navController: NavController) {
    composable(route = Roots.Tools.route) {
        Text(text = "Tools")
    }
}