package com.example.mytrainingpal.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.example.mytrainingpal.*

sealed class Screen(val route: String, val label: String, val icon: ImageVector, val resourceId: Int) {
    object Home : Screen("homeMain", "Home", Icons.Default.Home, 0)
    object MusclePainMain : Screen("musclePainMain", "Muscle Pain", Icons.Default.SentimentVeryDissatisfied, 1)
    object TrainingMain : Screen("trainingMain", "Training", Icons.Default.FitnessCenter, 2)
    object CalendarMain : Screen("calendarMain", "Calendar", Icons.Default.CalendarToday, 3)
    object Settings : Screen("settingsMain", "Settings", Icons.Default.Settings, 4)
}

val tabScreens = listOf(Screen.Home, Screen.MusclePainMain, Screen.TrainingMain, Screen.CalendarMain)

/** Find the Navigator in a new Fragment/Screen with

override fun onCreateView( /* ... */ ) {
    setContent {
        MyScreen(onNavigate = { dest -> findNavController().navigate(dest) })
    }
}
 **/

// Single source of truth for navigation. Should be instantiated only once!!!
// navigate calls should happen only here. Expose them through passing functions to the Composable
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "home"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = "home",
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
        }
        navigation(
            route = "settings",
            startDestination = Screen.Settings.route
        ) {
            composable(Screen.Settings.route) { SettingsScreen(navController) }
        }
        // Use those to maintain several back stacks for navigation
        navigation(
            route = "musclePain",
            startDestination = Screen.MusclePainMain.route
        ) {
            composable(Screen.MusclePainMain.route) { MusclePainScreen(navController) }
            // TODO: Add MusclePainDetailsScreen and so on
        }
        navigation(
            route = "calendar",
            startDestination = Screen.CalendarMain.route
        ) {
            composable(Screen.CalendarMain.route) { CalendarScreen(navController) }
            // TODO: Add CalendarDetailsScreen and so on
        }
        // Use those to maintain several back stacks for navigation
        navigation(
            route = "training",
            startDestination = Screen.TrainingMain.route
        ) {
            composable(Screen.TrainingMain.route) { TrainingScreen(navController) }
            // TODO: Add further Trainings Screens
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        tabScreens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, "") },
                label = { Text(screen.label) },
                alwaysShowLabel = false,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
