package com.example.mytrainingpal.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails
import kotlinx.coroutines.delay

// Inspired by: https://stackoverflow.com/questions/69230364/how-can-i-make-my-composable-recompose-with-a-for-loop-update

@Composable
fun ToggleScreen(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    //breakDurationFromSettings: Long
) {
    var breakDuration: Long = 500 //6500L
    var breakRunning: Boolean by remember { mutableStateOf(false) }
    // TODO: get break duration from settings and pass it to Break composable
    // how many exercise types in the list
    var currentExerciseCounter: Int by remember { mutableStateOf(0) }
    // counter of completed set for a given exercise
    var currentExerciseSetCounter: Int by remember { mutableStateOf(0) }
    // sum of all sets of all exercise types
    var totalSets: Int by remember { mutableStateOf(calculateTotalSets(exerciseList)) }
    // counter of completed set for all exercises
    var setCounter: Int by remember { mutableStateOf(0) }
    // format
    var updatedReps: MutableList<String> by remember { mutableStateOf(mutableListOf<String>()) }


    // each exercise screen should be shown a "exerciseSet" amount of times
    if (currentExerciseCounter == exerciseList.size) {
        LaunchedEffect(key1 = 0) {
            delay(0)
            println(exerciseList)
            navController.navigate(Screen.Settings.route)
        }
    } else if (breakRunning) {
        Break(
            navController,
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
            totalSets = totalSets,
            currentSet = setCounter,
        )
        LaunchedEffect(key1 = breakDuration) {
            delay(breakDuration)
            breakRunning = !breakRunning

        }
    } else {
        // TODO: Standing Calf exercise complete bugs the ExerciseScreen!
        CurrentExercise(
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
            currentExerciseSet = currentExerciseSetCounter,
            totalSets = totalSets,
            currentExerciseSetCounter = currentExerciseSetCounter,
            currentSet = setCounter,
            goToBreak = {
                breakRunning = !breakRunning
                if (currentExerciseSetCounter == exerciseList[currentExerciseCounter].second.sets - 1) {
                    updatedReps.add(it.toString())
                    exerciseList[currentExerciseCounter].second.reps = updatedReps.joinToString(",")
                    updatedReps.clear()
                    currentExerciseSetCounter = 0
                    currentExerciseCounter++
                } else {
                    currentExerciseSetCounter++
                }
                updatedReps.add(it.toString())
                setCounter++
            },

            //onRepUpdate = {
            //  updatedRepsAsInt.add(it)
            //}
        )
    }
}

fun calculateTotalSets(exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>): Int {
    var totalSets = 0
    for (i in 0 until exerciseList.size) {
        totalSets += exerciseList[i].second.sets
    }
    return totalSets
}


