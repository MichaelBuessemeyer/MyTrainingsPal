package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.MusclePainEntryMapConstants
import com.example.mytrainingpal.model.entities.Muscle

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
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        MusclePainWidget(
            navigateToMusclePain = navigateToMusclePain,
            soreMuscles = mutableListOf(
                Pair(
                    Muscle(name = "Right Biceps"), MusclePainEntryMapConstants.MODERATE_PAIN
                ),
                Pair(Muscle(name = "Left Biceps"), MusclePainEntryMapConstants.MODERATE_PAIN),
                Pair(Muscle(name = "Right Pectoralis"), MusclePainEntryMapConstants.SEVERE_PAIN),
                Pair(Muscle(name = "Left Pectoralis"), MusclePainEntryMapConstants.SEVERE_PAIN)
            ),
            showEditButton = true,
        )
        OverallRecordsCard()
        OverallRecordsCard()
        LastTrainingStatCard(thisTraining = false)
    }
}
