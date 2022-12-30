package com.example.mytrainingpal.screens

import android.graphics.Color
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.StatRow
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.WidgetCard

@Composable
fun WorkoutFinishedScreen(navController: NavController) {
    TabScreen(
        tabContent = {
            WorkoutFinishedContent()
        },
        topBarTitle = Screen.TrainingFinished.label,
        topBarIcon = Screen.TrainingFinished.icon,
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
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
fun WorkoutFinishedContent() {
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
                            title = "Time",
                            stat = 20,
                            imageVector = Icons.Default.AccessTime,
                            showTitle = false
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
    }
}