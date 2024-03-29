package com.arwani.ahmad.glimovie

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arwani.ahmad.glimovie.ui.components.BottomBar
import com.arwani.ahmad.glimovie.ui.components.TopMovieBar
import com.arwani.ahmad.glimovie.ui.favorite.FavoriteScreen
import com.arwani.ahmad.glimovie.ui.home.MainScreen
import com.arwani.ahmad.glimovie.ui.info.InfoScreen
import com.arwani.ahmad.glimovie.ui.navigation.Screen
import com.arwani.ahmad.glimovie.ui.profile.ProfileScreen
import com.arwani.ahmad.glimovie.ui.search.SearchScreen
import com.arwani.ahmad.glimovie.ui.trailer.MovieExoScreen

@Composable
fun JetMovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                TopMovieBar(canNavigate = false, title = "Gli Movie")
            }
        },
        bottomBar = {
            if (currentRoute != Screen.Splash.route && currentRoute != Screen.Info.route && currentRoute != Screen.Search.route) {
                BottomBar(navController = navController)
            }
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
                MainScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Info.createRoute(movieId))
                    },
                    navigateToSearch = { navController.navigate(Screen.Search.route) }
                )
            }
            composable(
                route = Screen.Info.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("movieId")
                if (id != null) {
                    InfoScreen(movieId = id) {
                        navController.navigateUp()
                    }
                }
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    navigate = { navController.navigateUp() },
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Info.createRoute(movieId))
                    })
            }
            composable(Screen.Trailer.route) {
                MovieExoScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navigateToDetail = { movieId ->
                    navController.navigate(Screen.Info.createRoute(movieId))
                })
            }
        }
    }
}