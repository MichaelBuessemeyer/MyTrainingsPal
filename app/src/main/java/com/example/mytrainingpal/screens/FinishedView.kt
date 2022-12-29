package com.example.mytrainingpal.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen

@Composable
fun FinishedScreen(navController: NavController) {
    TabScreen(
        tabContent = {
            WorkoutFinishedContent()
        },
        topBarTitle = Screen.WorkoutFinished.route,
        topBarIcon = Screen.WorkoutFinished.icon,
        navController = navController
    )
}

@Composable
fun WorkoutFinishedContent() {

}