package com.example.mytrainingpal.components

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.mytrainingpal.screens.BreakScreen
import com.example.mytrainingpal.screens.CurrentExerciseScreen
import kotlinx.coroutines.delay

// inspired by https://stackoverflow.com/questions/69230364/how-can-i-make-my-composable-recompose-with-a-for-loop-update

@Composable
fun ToggleComposable(navController: NavController) {
    // takes as parameters:
    // list of exercises (as observable state) - exercise names, reps, sets, exerciseDuration
    // break default duration
    // int = how many times should to toggle (could be inferred from list.length -1)

    var delayTime = 3000L
    var isTimeElapsed by remember { mutableStateOf(false) }
    // LaunchedEffect: "run suspend functions on the scope of a composable

    if (isTimeElapsed) {
        BreakScreen(navController)
    } else {
        CurrentExerciseScreen()
    }
    // block is a lambda
    LaunchedEffect(key1 = delayTime) {
        for (i in 1..100) {
            delay(delayTime) // update once a second
            isTimeElapsed = !isTimeElapsed
        }
    }
}


