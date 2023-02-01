package com.arwani.ahmad.glimovie.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arwani.ahmad.glimovie.ui.navigation.NavigationItem
import com.arwani.ahmad.glimovie.ui.navigation.Screen
import com.arwani.ahmad.glimovie.ui.theme.Blue100
import com.arwani.ahmad.glimovie.ui.theme.Blue900
import com.arwani.ahmad.glimovie.ui.theme.Green100


@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute != Screen.Splash.route) {
        val navigationItems = listOf(
            NavigationItem(title = "Home", icon = Icons.Default.Home, screen = Screen.Home),
            NavigationItem(
                title = "Profile", icon = Icons.Default.AccountCircle, screen = Screen.Profile
            )
        )
        BottomNavigation(
            backgroundColor = Blue900,
            contentColor = Green100,
        ) {
            navigationItems.map { item ->
                BottomNavigationItem(selected = currentRoute == item.screen.route, onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }, label = { Text(item.title) },
                    unselectedContentColor = Blue100,
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    })
            }
        }
    }

}