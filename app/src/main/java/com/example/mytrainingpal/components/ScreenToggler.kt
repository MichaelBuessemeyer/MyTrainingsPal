package com.example.mytrainingpal.components


import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key.Companion.Break
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavController
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.screens.BreakScreen
import com.example.mytrainingpal.screens.CurrentExerciseScreen
import com.example.mytrainingpal.screens.SettingsScreen
import com.example.mytrainingpal.util.ExerciseDetails
import kotlinx.coroutines.delay

// inspired by https://stackoverflow.com/questions/69230364/how-can-i-make-my-composable-recompose-with-a-for-loop-update

@Composable
fun ToggleComposable(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    breakDurationFromSettings: Long = 2000L
) {

/* THOUGHTS
 takes as parameters:
 list of exercises (as observable state) - exercise names, reps, sets, exerciseDuration
 break default duration
 int = how many times should to toggle (could be inferred from list.length)

    Exercise0 - 1/4
    Break
    Exercise0 - 2/4
    Break
    Exercise0 - 3/4
    Break
    Exercise0 - 4/4
    Break
    Exercise1 - 1/4
    Break
    Exercise1 - 2/4
    Break
    Exercise1 - 3/4
    Break
    Exercise1 - 4/4
    Break
    Exercise2 - 1/3
    Break
    Exercise2 - 2/3
    Break
    Exercise2 - 3/3
    */


    var exerciseDuration = 3000L
    var pauseDuration = 6500L
    var currentExerciseName: String by remember { mutableStateOf("") }
    var isTimeElapsed by remember { mutableStateOf(false) }


    if (!isTimeElapsed) {
        BreakScreen(navController)
    } else {
        CurrentExerciseScreen(currentExerciseName, 3, 2)
    }

    LaunchedEffect(key1 = exerciseDuration, key2 = pauseDuration) {

        if (exerciseList.isNotEmpty()) {
            for (i in 0..exerciseList.size) {
                if(i == exerciseList.size){
                    delay(exerciseDuration)
                    navController.navigate(Screen.Settings.route)
                } else {
                currentExerciseName = exerciseList[i].first.name
                isTimeElapsed = !isTimeElapsed
                    delay(exerciseDuration)
                    if(i != exerciseList.size - 1){
                        isTimeElapsed = !isTimeElapsed
                        delay(pauseDuration)
                    }
                }
            }
        }
    }
}


/* ------------------------
var delayTime = 3000L
var currentExerciseName: String by remember { mutableStateOf("") }
var isTimeElapsed by remember { mutableStateOf(false) }

LaunchedEffect(key1 = delayTime) {
    var flag = true
    if (exerciseList.isNotEmpty()) {
        for (i in 0..exerciseList.size) {
            if (flag) {
                currentExerciseName = exerciseList[i].first.name
                for (j in 0..4) {
                    delay(delayTime)
                    isTimeElapsed = !isTimeElapsed
                }
                flag = false
            } else {
                delay(delayTime)
                isTimeElapsed = !isTimeElapsed
            }
        }
    }
}


if (isTimeElapsed) {
    BreakScreen(navController)
} else {
    CurrentExerciseScreen(currentExerciseName, 3, 2)
}

}





