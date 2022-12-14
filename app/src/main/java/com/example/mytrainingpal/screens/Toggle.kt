package com.example.mytrainingpal.screens

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.mytrainingpal.composables.Break
import com.example.mytrainingpal.composables.CurrentExercise
import com.example.mytrainingpal.composables.Screen
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.view_models.PreferencesViewModel
import com.example.mytrainingpal.preferences.PreferencesConstants
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.TimeHolder
import kotlinx.coroutines.delay
import java.util.*

// Inspired by: https://stackoverflow.com/questions/69230364/how-can-i-make-my-composable-recompose-with-a-for-loop-update

@Composable
fun ToggleScreen(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    endTime: TimeHolder,
    preferencesViewModel: PreferencesViewModel
) {
    val preferencesState by preferencesViewModel.allPreferences.collectAsState(mapOf<String, Any>())
    val breakTime: Int =
        (preferencesState[PreferencesConstants.DEFAULT_BREAK_KEY.name] ?: 30) as Int

    val breakDuration: Long = breakTime * 1000L
    var breakRunning: Boolean by remember { mutableStateOf(false) }
    // counts how many exercises out of the workout list are completed
    var currentExerciseCounter: Int by remember { mutableStateOf(0) }
    // counts how many sets required for the current exercise are completed
    var currentExerciseSetCounter: Int by remember { mutableStateOf(0) }
    // sum of all sets of all exercises in the list
    val totalSets: Int by remember { mutableStateOf(calculateTotalSets(exerciseList)) }
    // counts how many sets are completed of total sets
    var setCounter: Int by remember { mutableStateOf(0) }
    // updated list os reps for a given exercise
    val updatedReps: MutableList<String> by remember { mutableStateOf(mutableStateListOf<String>()) }


    // each given exercise screen should be shown a "exerciseSet" amount of times
    if (currentExerciseCounter == exerciseList.size) {
        LaunchedEffect(key1 = 0) {
            delay(0)
            endTime.value = Date()
            navController.navigate(Screen.TrainingFinished.route)
        }
    } else if (breakRunning) {
        Break(
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
            totalSets = totalSets,
            currentSet = setCounter,
            currentExerciseSetCounter = currentExerciseSetCounter,
            breakTime = breakTime,
        )
        LaunchedEffect(key1 = breakDuration) {
            delay(breakDuration)
            breakRunning = !breakRunning

        }
    } else {
        CurrentExercise(
            exerciseList = exerciseList,
            currentExerciseIndex = currentExerciseCounter,
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
            }
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


