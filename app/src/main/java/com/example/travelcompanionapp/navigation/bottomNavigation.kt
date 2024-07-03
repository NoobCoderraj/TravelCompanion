package com.example.travelcompanionapp.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import com.example.travelcompanionapp.navigation.Routes

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Home", Routes.Home.routes, Icons.Default.Home),
        BottomNavItem("Planning", Routes.Profile.routes, Icons.Default.Person),
        BottomNavItem("Settings", Routes.Plans.routes, Icons.Default.Settings)
    )

    BottomNavigation(backgroundColor = Color(0xFFD3D3D3) ){

        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
