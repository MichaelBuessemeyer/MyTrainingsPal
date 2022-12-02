package com.example.mytrainingpal.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sports
import androidx.compose.runtime.Composable

@Composable
fun ExerciseFloatingButton(navigateToTrainingScreen: () -> Unit) {
    FloatingActionButton(onClick = { navigateToTrainingScreen() }) {
        Icon(imageVector = Icons.Default.Sports, contentDescription = "Start Training")
    }
}