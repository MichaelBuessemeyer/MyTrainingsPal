package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.states.rememberTodaysMusclePainEntryState

@Composable
fun HomeScreen(navController: NavController, musclePainEntryViewModel: MusclePainEntryViewModel) {
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
            musclePainEntryViewModel
        )
    }, navController = navController)
}

@Composable
fun HomeScreenContent(
    navigateToMusclePain: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    musclePainEntryViewModel: MusclePainEntryViewModel
) {
    val todaysMusclePainEntry = rememberTodaysMusclePainEntryState(musclePainEntryViewModel)

    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        if (todaysMusclePainEntry != null) {
            EnterPainPrompt(navigateToMusclePain = navigateToMusclePain)
        }
        MusclePainWidget(navigateToMusclePain = navigateToMusclePain)
        OverallRecordsCard()
        OverallRecordsCard()
        LastTrainingStatCard(thisTraining = false)
    }
}
