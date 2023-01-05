package com.example.mytrainingpal.composables

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
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.view_models.*
import com.example.mytrainingpal.screens.*
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.IntHolder
import com.example.mytrainingpal.util.TimeHolder
import java.util.*

// all of this is very inspired by https://developer.android.com/jetpack/compose/navigation

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val group: String
) {
    object Home : Screen("homeMain", "Home", Icons.Default.Home, RouteGroups.HOME.route)
    object MusclePainMain :
        Screen(
            "musclePainMain",
            "Muscle Pain",
            ShoulderPainIcon(),
            RouteGroups.MUSCLE_PAIN.route
        )

    object TrainingMain :
        Screen("trainingMain", "Training", Icons.Default.FitnessCenter, RouteGroups.TRAINING.route)

    object TrainingsPreview : Screen(
        "trainingsPreview",
        "TrainingsPreview",
        Icons.Default.FitnessCenter,
        RouteGroups.TRAINING.route
    )

    object Toggle :
        Screen("toggle", "Toggle", Icons.Default.Settings, RouteGroups.TRAINING.route)

    object TrainingFinished :
        Screen(
            "trainingFinished",
            "Finished",
            Icons.Default.Celebration,
            RouteGroups.TRAINING.route
        )

    object CalendarMain :
        Screen("calendarMain", "Calendar", Icons.Default.CalendarToday, RouteGroups.CALENDAR.route)

    object Settings :
        Screen("settingsMain", "Settings", Icons.Default.Settings, RouteGroups.SETTINGS.route)


}

enum class RouteGroups(val route: String) {
    HOME("home"),
    MUSCLE_PAIN("musclePain"),
    TRAINING("training"),
    CALENDAR("calendar"),
    SETTINGS("settings"),
}

val tabScreens =
    listOf(Screen.Home, Screen.MusclePainMain, Screen.TrainingMain, Screen.CalendarMain)

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
            composable(Screen.Home.route) {
                // TODO: to avoid repeating code increase the following code's scope
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current
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
                        val workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel =
                            viewModel(
                                it,
                                "WorkoutEntryExerciseMapViewModel",
                                factory
                            )
                        val preferencesViewModel: PreferencesViewModel = viewModel(
                            it,
                            "PreferencesViewModel",
                            factory
                        )
                        HomeScreen(
                            navController,
                            musclePainEntryViewModel,
                            workoutEntryExerciseMapViewModel,
                            musclePainEntryMapViewModel,
                            preferencesViewModel
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
        }
        navigation(
            route = RouteGroups.SETTINGS.route,
            startDestination = Screen.Settings.route
        ) {
            composable(Screen.Settings.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current
                        )
                        val preferencesViewModel: PreferencesViewModel = viewModel(
                            it,
                            "PreferencesViewModel",
                            factory
                        )
                        SettingsScreen(
                            navController,
                            preferencesViewModel,
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
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
                            LocalContext.current
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
                            LocalContext.current
                        )
                        val workoutEntryViewModel: WorkoutEntryViewModel = viewModel(
                            it,
                            "WorkoutEntryViewModel",
                            factory
                        )
                        val musclePainEntryMapViewModel: MusclePainEntryMapViewModel = viewModel(
                            it,
                            "MusclePainEntryMapViewModel",
                            factory
                        )
                        CalendarScreen(
                            navController,
                            workoutEntryViewModel,
                            musclePainEntryMapViewModel
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
        }
        // Use those to maintain several back stacks for navigation
        navigation(
            route = RouteGroups.TRAINING.route,
            startDestination = Screen.TrainingMain.route
        ) {
            // list of exercises for current workout. gets updated by reference
            val exercises = mutableListOf<Pair<Exercise, ExerciseDetails>>()
            val duration = IntHolder(0)
            val startTime = TimeHolder(Date())
            val endTime = TimeHolder(Date())
            composable(Screen.TrainingMain.route) { TrainingScreen(navController, duration) }
            composable(Screen.TrainingsPreview.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current
                        )
                        val musclePainEntryViewModel: MusclePainEntryViewModel = viewModel(
                            it,
                            "MusclePainEntryViewModel",
                            factory
                        )
                        val musclePainEntryMapViewModel: MusclePainEntryMapViewModel = viewModel(
                            it,
                            "MusclePainEntryMapViewModel",
                            factory
                        )
                        val exerciseMuscleMapViewModel: ExerciseMuscleMapViewModel = viewModel(
                            it,
                            "ExerciseMuscleMapViewModel",
                            factory
                        )
                        val preferencesViewModel: PreferencesViewModel = viewModel(
                            it,
                            "PreferencesViewModel",
                            factory
                        )
                        TrainingsPreviewScreen(
                            navController,
                            duration,
                            exercises,
                            musclePainEntryViewModel,
                            musclePainEntryMapViewModel,
                            exerciseMuscleMapViewModel,
                            preferencesViewModel,
                            startTime = startTime
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
            composable(Screen.Toggle.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current
                        )
                        val preferencesViewModel: PreferencesViewModel = viewModel(
                            it,
                            "PreferencesViewModel",
                            factory
                        )
                        ToggleScreen(
                            navController,
                            exercises,
                            endTime,
                            preferencesViewModel
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
            composable(Screen.TrainingFinished.route) {
                val owner = LocalViewModelStoreOwner.current

                if (owner != null) {
                    owner.let {
                        val factory = GenericViewModelFactory(
                            LocalContext.current
                        )
                        val workoutEntryViewModel: WorkoutEntryViewModel = viewModel(
                            it,
                            "WorkoutEntryViewModel",
                            factory
                        )
                        val workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel =
                            viewModel(
                                it,
                                "WorkoutEntryExerciseMapViewModel",
                                factory
                            )
                        val preferencesViewModel: PreferencesViewModel = viewModel(
                            it,
                            "PreferencesViewModel",
                            factory
                        )

                        TrainingFinishedScreen(
                            navController,
                            exercises,
                            startTime,
                            endTime,
                            workoutEntryViewModel,
                            workoutEntryExerciseMapViewModel,
                            preferencesViewModel
                        )
                    }
                } else {
                    Text("Still Loading View Model")
                }
            }
        }
    }
}
