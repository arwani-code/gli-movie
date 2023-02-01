package com.arwani.ahmad.glimovie.ui.navigation

sealed class Screen(val route: String){
    object Home : Screen("Home")
    object Profile : Screen("Profile")
    object Splash : Screen("Splash")
    object Info : Screen("Info")
}