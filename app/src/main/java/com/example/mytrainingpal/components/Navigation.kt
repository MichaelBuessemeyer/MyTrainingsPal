package com.example.mytrainingpal.components

import android.app.Application
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.mytrainingpal.model.GenericViewModelFactory
import com.example.mytrainingpal.model.view_models.ExerciseViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.MuscleViewModel
import com.example.mytrainingpal.screens.*

// all of this is very inspired by https://developer.android.com/jetpack/compose/navigation

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val resourceId: Int
) {
    object Home : Screen("homeMain", "Home", Icons.Default.Home, 0)
    object MusclePainMain :
        Screen("musclePainMain", "Muscle Pain", Icons.Default.SentimentVeryDissatisfied, 1)

    object TrainingMain : Screen("trainingMain", "Training", Icons.Default.FitnessCenter, 2)
    object CalendarMain : Screen("calendarMain", "Calendar", Icons.Default.CalendarToday, 3)
    object Settings : Screen("settingsMain", "Settings", Icons.Default.Settings, 4)
}

enum class RouteGroups(val route: String) {
    HOME("home"),
    MUSCLE_PAIN("musclePain"),
    TRAINING("training"),
    CALENDAR("calendar"),
    SETTINGS("settings")
}

val tabScreens =
    listOf(Screen.Home, Screen.MusclePainMain, Screen.TrainingMain, Screen.CalendarMain)

/** Find the Navigator in a new Fragment/View with

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
    startDestination: String = RouteGroups.HOME.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = RouteGroups.HOME.route,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
        }
        navigation(
            route = RouteGroups.SETTINGS.route,
            startDestination = Screen.Settings.route
        ) {
            composable(Screen.Settings.route) { SettingsScreen(navController) }
        }
        // Use those to maintain several back stacks for navigation
        navigation(
            route = RouteGroups.MUSCLE_PAIN.route,
            startDestination = Screen.MusclePainMain.route
        ) {
            composable(Screen.MusclePainMain.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current.applicationContext
                                    as Application
                        )
                        val musclePainEntryMapViewModel: MusclePainEntryMapViewModel = viewModel(
                            it,
                            "MusclePainEntryMapViewModel",
                            factory
                        )
                        val musclePainEntryViewModel: MusclePainEntryViewModel = viewModel(
                            it,
                            "MusclePainEntryViewModel",
                            factory
                        )
                        MusclePainScreen(
                            navController,
                            musclePainEntryMapViewModel = musclePainEntryMapViewModel,
                            musclePainEntryViewModel = musclePainEntryViewModel
                        )
                    }
                } else Text("")
            }
            // TODO: Add MusclePainDetailsScreen and so on
        }
        navigation(
            route = RouteGroups.CALENDAR.route,
            startDestination = Screen.CalendarMain.route
        ) {
            composable(Screen.CalendarMain.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current.applicationContext
                                    as Application
                        )
                        val muscleViewModel: MuscleViewModel = viewModel(
                            it,
                            "MuscleViewModel",
                            factory
                        )
                        val exerciseViewModel: ExerciseViewModel = viewModel(
                            it,
                            "ExerciseViewModel",
                            factory
                        )
                        CalendarScreen(navController, muscleViewModel, exerciseViewModel)
                    }
                } else {
                    Text("Still Loading View Model")
                }

            }
            // TODO: Add CalendarDetailsScreen and so on
        }
        // Use those to maintain several back stacks for navigation
        navigation(
            route = RouteGroups.TRAINING.route,
            startDestination = Screen.TrainingMain.route
        ) {
            composable(Screen.TrainingMain.route) { TrainingScreen(navController) }
            // TODO: Add further Trainings Screens
        }
    }
}
