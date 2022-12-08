package com.example.mytrainingpal.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable

@Composable
fun StartExerciseFloatingButton(navigateToTrainingScreen: () -> Unit) {
    FloatingActionButton(onClick = { navigateToTrainingScreen() }) {
        Icon(imageVector = Icons.Default.FitnessCenter, tint = MaterialTheme.colors.onSecondary, contentDescription = "Start Training")
    }
}