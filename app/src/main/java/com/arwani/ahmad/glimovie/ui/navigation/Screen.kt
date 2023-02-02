package com.arwani.ahmad.glimovie.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Splash : Screen("splash")
    object Info : Screen("home/{movieId}") {
        fun createRoute(movieId: Int) = "home/$movieId"
    }

    object Search : Screen("search")
}