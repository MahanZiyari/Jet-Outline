package com.mahan.compose.jetoutline.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mahan.compose.jetoutline.ui.screens.home.HomeScreen
import com.mahan.compose.jetoutline.ui.screens.home.HomeScreenViewModel

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun AppNavigation(
    homeScreenViewModel: HomeScreenViewModel
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Destinations.HomeScreen.name,
    ) {
        // HomeScreen
        composable(route = Destinations.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                viewModel = homeScreenViewModel
            )
        }
    }
}