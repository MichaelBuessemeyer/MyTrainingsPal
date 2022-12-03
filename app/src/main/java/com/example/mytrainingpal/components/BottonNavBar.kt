package com.example.mytrainingpal.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(backgroundColor = MaterialTheme.colors.primary) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        tabScreens.forEach { screen ->
            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onPrimary,
                icon = { Icon(screen.icon, "") },
                label = { Text(screen.label) },
                alwaysShowLabel = false,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) { }
                }
            )
        }
    }
}
