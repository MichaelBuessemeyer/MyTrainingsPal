package com.example.mytrainingpal.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen

@Composable
fun MusclePainScreen(navController: NavController) {
    TabScreen({ Greeting(name = "Muscle Pain") }, topBarTitle = Screen.MusclePainMain.label, topBarIcon = Screen.MusclePainMain.icon,navController = navController)

}