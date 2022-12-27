package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryExerciseMapViewModel
import com.example.mytrainingpal.states.rememberTodaysMusclePainEntryState

@Composable
fun HomeScreen(
    navController: NavController,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel
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
            workoutEntryExerciseMapViewModel = workoutEntryExerciseMapViewModel
        )
    }, topBarTitle = null, topBarIcon = null, navController = navController)
}

@Composable
fun HomeScreenContent(
    navigateToMusclePain: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    musclePainEntryViewModel: MusclePainEntryViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel
) {
    val todaysMusclePainEntry = rememberTodaysMusclePainEntryState(musclePainEntryViewModel)

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
        UserNameWithSettings(navigateToSettings)
        if (todaysMusclePainEntry == null) {
            EnterPainPrompt(navigateToMusclePain = navigateToMusclePain)
        }
        MusclePainWidget(navigateToMusclePain = navigateToMusclePain)
        OverallRecordsCard(workoutEntryExerciseMapViewModel)
        LastTrainingStatCard(workoutEntryExerciseMapViewModel)
    }
}
