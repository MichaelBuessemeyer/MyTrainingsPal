package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.PreferencesViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryExerciseMapViewModel
import com.example.mytrainingpal.prefrences.PreferencesConstants
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState

@Composable
fun HomeScreen(
    navController: NavController,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
    preferencesViewModel: PreferencesViewModel
) {
    TabScreen(tabContent = {
        HomeScreenContent(navigateToMusclePain = {
            navController.navigate(
                RouteGroups.MUSCLE_PAIN.route
            )
        }, navigateToSettings = {
            navController.navigate(
                RouteGroups.SETTINGS.route
            )
        },
            musclePainEntryViewModel = musclePainEntryViewModel,
            workoutEntryExerciseMapViewModel = workoutEntryExerciseMapViewModel,
            musclePainEntryMapViewModel = musclePainEntryMapViewModel,
            preferencesViewModel = preferencesViewModel
        )
    }, topBarTitle = null, topBarIcon = null, navController = navController)
}

@Composable
fun HomeScreenContent(
    navigateToMusclePain: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    musclePainEntryViewModel: MusclePainEntryViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
    preferencesViewModel: PreferencesViewModel
) {
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)

    RememberFetchMusclePainEntryWithMuscles(todaysMusclePainEntry, musclePainEntryMapViewModel)
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    RememberAddingSoreMusclesToList(musclePainEntryMapViewModel, soreMuscles)

    val preferencesState by preferencesViewModel.allPreferences.collectAsState(mapOf<String, Any>())
    val userName: String = preferencesState[PreferencesConstants.USERNAME_KEY.name].toString()

    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Portrait,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .padding(8.dp)
                .size(120.dp)
        )
        UserNameWithSettings(userName, navigateToSettings)
        if (todaysMusclePainEntry == null) {
            EnterPainPrompt(navigateToMusclePain = navigateToMusclePain)
        }
        MusclePainWidget(
            navigateToMusclePain = navigateToMusclePain,
            soreMuscles = soreMuscles,
            showEditButton = true,
        )
        OverallRecordsCard(workoutEntryExerciseMapViewModel)
        LastTrainingStatCard(workoutEntryExerciseMapViewModel)
    }
}
