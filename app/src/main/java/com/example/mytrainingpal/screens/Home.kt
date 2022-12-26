package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel

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
fun HomeScreenContent(navigateToMusclePain: () -> Unit = {}, navigateToSettings: () -> Unit = {}, musclePainEntryViewModel: MusclePainEntryViewModel
) {
    // TODO: this list is apparently returned empty, probably because I still couldn't to add mock data to the DB
    val allMusclePainEntries by musclePainEntryViewModel.allMusclePainEntries.observeAsState(listOf())

    // test variable to see if prompt gets displayed
    var wasPainEntered by remember { mutableStateOf(true) }

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
