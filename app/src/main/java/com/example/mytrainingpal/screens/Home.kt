package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*

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
    }, topBarTitle = null, topBarIcon = null, navController = navController)
}

@Composable
fun HomeScreenContent(navigateToMusclePain: () -> Unit = {}, navigateToSettings: () -> Unit = {}) {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        MusclePainWidget(navigateToMusclePain = navigateToMusclePain)
        OverallRecordsCard()
        OverallRecordsCard()
        LastTrainingStatCard(thisTraining = false)
    }
}
