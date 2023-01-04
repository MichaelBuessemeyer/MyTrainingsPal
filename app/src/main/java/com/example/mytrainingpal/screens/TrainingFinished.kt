package com.example.mytrainingpal.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

@Composable
fun TrainingFinishedScreen(
    navController: NavController,
    //immutable List?
    doneExercises: MutableList<Pair<Exercise, ExerciseDetails>>
) {
    TabScreen(
        tabContent = {
            TrainingFinishedContent(doneExercises)
        },
        topBarTitle = Screen.TrainingFinished.label,
        topBarIcon = Screen.TrainingFinished.icon,
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {
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

@Composable
fun TrainingFinishedContent(
    doneExercises: MutableList<Pair<Exercise, ExerciseDetails>>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WidgetCard(hasBorder = false) {
            Text(
                text = "Congrats Klaus!", //TODO get from preferences
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        //TODO replace with TrainingRecapCard
        var totalSets: Int = 0
        var totalWeightLifted: Int = 0
        var totalReps: Int = 0
        doneExercises.forEach() { (exercise, details) ->
            totalSets += details.sets
            totalWeightLifted += details.weight
            // Array splitting method: https://stackoverflow.com/questions/46038476/how-could-i-split-a-string-into-an-array-in-kotlin (visited 04.01.23)
            val repsArray: Array<String> = details.reps.split(",").toTypedArray()
            repsArray.forEach {
                totalReps += it.toInt()
            }
        }

        // TODO correct time!
        val totalWorkoutTime: Int = (totalReps / 3)


        WidgetCard(hasBorder = false) {
            Column (
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
                        StatRow(
                            title = "minutes",
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
        Row() {
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
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            onClick = { /*TODO opens Camera*/ },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "Take progress picture")
            Text("Take a progress picture")
        }
        doneExercises.forEach() { (exercise, details) ->
            ExerciseRecapWidget(
                exercise = exercise,
                weight = details.weight,
                reps = details.reps,
                sets = details.sets
            )
        }
/*        doneExercises.forEach() { (exercise, details) ->
            Text("exersise: ${exercise.name}")
            Text("weight: ${details.weight}")
            Text("reps: ${details.reps}")
            Text("sets: ${details.sets}")
        }*/
    }
}