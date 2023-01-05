package com.example.mytrainingpal.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.composables.*
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.view_models.PreferencesViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryExerciseMapViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryViewModel
import com.example.mytrainingpal.preferences.PreferencesConstants
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.TimeHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TrainingFinishedScreen(
    navController: NavController,
    doneExercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    startTime: TimeHolder,
    endTime: TimeHolder,
    workoutEntryViewModel: WorkoutEntryViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel,
    preferencesViewModel: PreferencesViewModel
) {
    TabScreen(
        tabContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var totalSets: Int = 0
                var totalWeightLifted: Int = 0
                var totalReps: Int = 0
                doneExercises.forEach { (_, details) ->
                    totalSets += details.sets
                    // Array splitting method: https://stackoverflow.com/questions/46038476/how-could-i-split-a-string-into-an-array-in-kotlin (visited 04.01.23)
                    val repsArray: Array<String> = details.reps.split(",").toTypedArray()
                    repsArray.forEach {
                        totalReps += it.toInt()
                        totalWeightLifted += details.weight * it.toInt()
                    }
                }


                val preferencesState by preferencesViewModel.allPreferences.collectAsState(mapOf<String, Any>())
                val userName: String =
                    (preferencesState[PreferencesConstants.USERNAME_KEY.name]
                        ?: "Your Name") as String

                // calculating how long the workout lasted with start and end time
                // Calculating: https://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances (04.01.23)
                val totalWorkoutTime: Int =
                    ((endTime.value.time - startTime.value.time) / 6000).toInt()
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    WidgetCard(hasBorder = false) {
                        Text(
                            text = "Congrats $userName!", //TODO get from preferences (look settings.kt)
                            style = MaterialTheme.typography.h1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(20.dp)
                        )
                    }

                    WidgetCard(hasBorder = false) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "This Training:")
                            Row {
                                Spacer(modifier = Modifier.width(20.dp))
                                Column(horizontalAlignment = Alignment.Start) {
                                    StatRow(
                                        title = "total sets",
                                        stat = totalSets,
                                        imageVector = Icons.Default.CalendarToday
                                    )
                                    StatRow(
                                        title = "kg lifted",
                                        stat = totalWeightLifted,
                                        imageVector = Icons.Default.FitnessCenter
                                    )
                                    // for demo purposes we show the time in seconds. normally it would be minutes
                                    StatRow(
                                        title = "seconds",
                                        stat = totalWorkoutTime,
                                        imageVector = Icons.Default.AccessTime
                                    )
                                    StatRow(
                                        title = "total reps",
                                        stat = totalReps,
                                        imageVector = Icons.Default.CalendarToday
                                    )
                                }
                            }
                        }

                    }
                    Spacer(modifier =Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Celebration,
                            contentDescription = "Congrats",
                            modifier = Modifier.size(100.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Celebration,
                            contentDescription = "Congrats",
                            modifier = Modifier.size(100.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Celebration,
                            contentDescription = "Congrats",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                            onClick = { /*TODO opens Camera*/ },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.background,
                                contentColor = MaterialTheme.colors.secondary
                            ),
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Take progress picture"
                            )
                            Text("Take a progress picture")
                        }
                    }
                    Spacer(modifier =Modifier.height(20.dp))
                    doneExercises.forEach { (exercise, details) ->
                        ExerciseRecapWidget(
                            exercise = exercise,
                            weight = details.weight,
                            reps = details.reps,
                            sets = details.sets
                        )
                    }
                }
            }

        },
        topBarTitle = Screen.TrainingFinished.label,
        topBarIcon = Screen.TrainingFinished.icon,
        navController = navController,
        withNavBar = false,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                saveWorkoutToDatabase(
                    workoutEntryViewModel,
                    startTime,
                    endTime,
                    doneExercises,
                    workoutEntryExerciseMapViewModel
                )
                navController.navigate((Screen.Home.route))
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Go to Home"
                )
            }
        }
    )
}

fun saveWorkoutToDatabase(
    workoutEntryViewModel: WorkoutEntryViewModel,
    startTime: TimeHolder,
    endTime: TimeHolder,
    doneExercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel
) {
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    coroutineScope.launch(Dispatchers.IO) {
        val workoutEntryId: Long = workoutEntryViewModel.insertWorkoutEntryOnCurrentThread(
            WorkoutEntry(
                date = startTime.value,
                startTime = startTime.value,
                endTime = endTime.value
            )
        )
        doneExercises.forEach { (exercise, details) ->
            workoutEntryExerciseMapViewModel.insertWorkoutEntryExerciseMap(
                WorkoutEntryExerciseMap(
                    workoutIdMap = workoutEntryId,
                    exerciseIdMap = exercise.exerciseId ?: 1L,
                    sets = details.sets.toLong(),
                    reps = details.reps,
                    weight = details.weight.toLong()
                )
            )
        }
    }
}