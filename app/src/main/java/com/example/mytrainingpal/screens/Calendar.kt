package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.WidgetCard
import com.example.mytrainingpal.model.view_models.ExerciseViewModel
import com.example.mytrainingpal.model.view_models.MuscleViewModel
import io.github.boguszpawlowski.composecalendar.StaticCalendar


@Composable
fun CalendarScreen(
    navController: NavController,
    muscleViewModel: MuscleViewModel,
    exerciseViewModel: ExerciseViewModel
) {
    TabScreen(
        tabContent = { CalendarScreenContent(muscleViewModel, exerciseViewModel) },
        topBarIcon = Screen.CalendarMain.icon,
        topBarTitle = Screen.CalendarMain.label,
        navController = navController
    )
}

@Composable
fun CalendarScreenContent(muscleViewModel: MuscleViewModel, exerciseViewModel: ExerciseViewModel) {
    val allExercises by exerciseViewModel.allExercises.observeAsState(listOf())
    Column {
        StaticCalendar(
            horizontalSwipeEnabled = false,
            monthContainer = { content ->
                WidgetCard(
                    hasBorder = false,
                    content = { content(PaddingValues(4.dp)) },
                )
            },
            dayContent = { day ->
                var color = MaterialTheme.colors.background

                //TODO: mark depending on recorded pain or training
                if ((day.date.dayOfMonth % 2) == 0) {
                    color = MaterialTheme.colors.secondary
                }

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = color, shape = RoundedCornerShape(4.dp))
                        .fillMaxWidth()

                ) {
                    Text(
                        text = day.date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            })
        if (allExercises.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(allExercises) { exercise ->
                    Greeting(name = exercise.name)
                }
            }
        } else {
            Text("Still loading exer")
        }
    }
}