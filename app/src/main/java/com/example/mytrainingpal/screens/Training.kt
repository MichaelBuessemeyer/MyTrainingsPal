package com.example.mytrainingpal.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.TabScreen


@Composable
fun TrainingScreen(navController: NavController) {
    TabScreen(tabContent = { Greeting(name = "Training") }, navController = navController)
}