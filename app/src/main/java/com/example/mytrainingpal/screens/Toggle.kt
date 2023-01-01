package com.example.mytrainingpal.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails
import kotlinx.coroutines.delay

// Reference https://stackoverflow.com/questions/69230364/how-can-i-make-my-composable-recompose-with-a-for-loop-update

@Composable
fun ToggleScreen(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    breakDurationFromSettings: Long = 1000L
) {
    var breakDuration: Long = 500L
    var breakRunning: Boolean by remember { mutableStateOf(false) }
    // TODO: get break duration from settings and pass it to Break composable
    var currentExerciseCounter: Int by remember { mutableStateOf(0) }
    var currentExerciseSetCounter: Int by remember { mutableStateOf(0) }
    var totalSets: Int by remember { mutableStateOf(calculateTotalSets(exerciseList)) }
    var currentSetCounter: Int by remember { mutableStateOf(0) }

    // each exercise screen should be shown a "exerciseSet" amount of times
    if (currentExerciseCounter == exerciseList.size) {
        LaunchedEffect(key1 = 0) {
            delay(0)
            navController.navigate(Screen.Settings.route)
        }
    } else if (breakRunning) {
        Break(
            navController,
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
        )
        LaunchedEffect(key1 = breakDuration) {
            delay(breakDuration)
            breakRunning = !breakRunning
        }
    } else {

        CurrentExercise(
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
            currentExerciseSet = currentExerciseSetCounter,
            totalSets = totalSets,
            currentSet = currentSetCounter
        ) {
            breakRunning = !breakRunning
            if (currentExerciseSetCounter == exerciseList[currentExerciseCounter].second.sets - 1) {
                currentExerciseSetCounter = 0
                currentExerciseCounter++
            } else {
                currentExerciseSetCounter++
            }
            currentSetCounter++
        }
    }
}

fun calculateTotalSets(exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>): Int {
    var totalSets = 0
    for (i in 0 until exerciseList.size) {
        totalSets += exerciseList[i].second.sets
    }
    return totalSets
}
