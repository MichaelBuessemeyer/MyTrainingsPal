package com.example.mytrainingpal.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabScreen(tabContent: @Composable () -> Unit, navController: NavController) {
    Scaffold(
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                tabContent()
            }
        },
        floatingActionButton = { StartExerciseFloatingButton { navController.navigate(Screen.TrainingMain.route) } },
        bottomBar = { BottomNavBar(navController) }
    )
}