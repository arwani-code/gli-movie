package com.arwani.ahmad.glimovie.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arwani.ahmad.glimovie.ui.components.BottomBar
import com.arwani.ahmad.glimovie.ui.home.MainScreen
import com.arwani.ahmad.glimovie.ui.navigation.Screen

@Composable
fun JetMovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                MainScreen()
            }
//            composable(Screen.Profile.route) {
//                ProfileScreen()
//            }
//            composable(Screen.Splash.route) {
//                SplashScreen(navController = navController)
//            }
        }
    }
}