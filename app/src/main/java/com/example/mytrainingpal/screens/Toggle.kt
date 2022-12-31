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
    //var currentSetCounter: Int by remember { mutableStateOf(0) }

    if (currentExerciseCounter == exerciseList.size) {
        LaunchedEffect(key1 = 0) {
            delay(0)
            navController.navigate(Screen.Settings.route)
        }
    } else if (breakRunning) {
        Break(navController)
        LaunchedEffect(key1 = breakDuration) {
            delay(breakDuration)
            breakRunning = !breakRunning
        }
    } else {
        CurrentExercise(
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
            //currentSetIndex = 999
        ) {
            breakRunning = !breakRunning
            currentExerciseCounter++
            //if (currentSetCounter == exerciseList[currentExerciseCounter].second.sets) {
              //  currentSetCounter = 0

            //}
            //currentSetCounter++
        }

    }
}