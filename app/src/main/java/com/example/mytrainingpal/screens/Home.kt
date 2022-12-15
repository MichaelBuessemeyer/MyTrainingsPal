package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
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
    }, navController = navController)
}

@Composable
fun HomeScreenContent(navigateToMusclePain: () -> Unit = {}, navigateToSettings: () -> Unit = {}) {

    var wasPainEntered by remember { mutableStateOf(true) }
    //TODO wasPainEntered should be updated in the model, toggled to true or false
    // in that case we need a Model instance passed into HomeScreenContent
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserNameWithSettings(navigateToSettings)
        if(wasPainEntered){
            EnterPainPrompt(navigateToMusclePain = navigateToMusclePain)
        }
        MusclePainWidget(navigateToMusclePain = navigateToMusclePain)
        OverallRecordsCard()
        OverallRecordsCard()
        LastTrainingStatCard(thisTraining = false)
    }
}
