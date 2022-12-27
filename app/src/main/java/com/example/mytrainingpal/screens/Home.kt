package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState

@Composable
fun HomeScreen(
    navController: NavController,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
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
            musclePainEntryMapViewModel = musclePainEntryMapViewModel
        )
    }, topBarTitle = null, topBarIcon = null, navController = navController)
}

@Composable
fun HomeScreenContent(
    navigateToMusclePain: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
) {
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)
    val exercise = Exercise(name = "Test", pathToGif = "somePath")
    val weight by remember { mutableStateOf(20) }
    var sets by remember { mutableStateOf(4) }
    var reps by remember { mutableStateOf(10) }

    RememberFetchMusclePainEntryWithMuscles(todaysMusclePainEntry, musclePainEntryMapViewModel)
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    RememberAddingSoreMusclesToList(musclePainEntryMapViewModel, soreMuscles)
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        if (todaysMusclePainEntry == null) {
            EnterPainPrompt(navigateToMusclePain = navigateToMusclePain)
        }
        MusclePainWidget(
            navigateToMusclePain = navigateToMusclePain,
            soreMuscles = soreMuscles,
            showEditButton = true,
        )
        ExerciseWidget(
            exercise,
            sets = sets,
            reps = reps,
            weight = weight,
            onRepsChanged = { reps = it },
            onSetsChanged = { sets = it })
        OverallRecordsCard()
        OverallRecordsCard()
        LastTrainingStatCard(thisTraining = false)
    }
}
