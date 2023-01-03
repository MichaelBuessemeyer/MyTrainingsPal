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
                            stat = 20,
                            imageVector = Icons.Default.CalendarToday
                        )
                        StatRow(
                            title = "kg lifted",
                            stat = 20,
                            imageVector = Icons.Default.FitnessCenter
                        )
                        StatRow(
                            title = "minutes",
                            stat = 20,
                            imageVector = Icons.Default.AccessTime
                        )
                        StatRow(
                            title = "total reps",
                            stat = 20,
                            imageVector = Icons.Default.FitnessCenter
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
        /*doneExercises.forEach() { (exercise, details) ->
            ExerciseWidget(
                exercise = exercise,
                weight = details.weight,
                reps = reps,
                sets = sets,
                onRepsChanged = {
                    reps = it
                    details.reps = List<String>(sets) { "$reps" }.joinToString(",")
                },
                onSetsChanged = {
                    sets = it
                    details.sets = it
                    details.reps = List<String>(sets) { "$reps" }.joinToString(",")
                }
            )
        }*/
        doneExercises.forEach() { (exercise, details) ->
            Text("exersise: ${exercise.name}")
            Text("weight: ${details.weight}")
            Text("reps: ${details.reps}")
            Text("sets: ${details.sets}")
        }
    }
}