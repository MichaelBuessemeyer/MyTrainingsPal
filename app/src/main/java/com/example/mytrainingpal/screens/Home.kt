package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Exercise

@Composable
fun HomeScreen(navController: NavController) {
    TabScreen(tabContent = {
        HomeScreenContent(navigateToMusclePain = {
            navController.navigate(
                RouteGroups.MUSCLE_PAIN.route
            )
        }, navigateToSettings = {
            navController.navigate(
                RouteGroups.SETTINGS.route
            )
        })
    }, navController = navController)
}

@Composable
fun HomeScreenContent(navigateToMusclePain: () -> Unit = {}, navigateToSettings: () -> Unit = {}) {
    val exercise: Exercise = Exercise(name = "Test", pathToGif = "somePath")
    val weight by remember { mutableStateOf(20) }
    var sets by remember { mutableStateOf(4) }
    var reps by remember { mutableStateOf(10) }
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        MusclePainWidget(navigateToMusclePain = navigateToMusclePain)
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
