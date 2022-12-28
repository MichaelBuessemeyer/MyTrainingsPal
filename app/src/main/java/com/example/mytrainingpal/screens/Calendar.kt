package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.CalendarListWidget
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.WidgetCard
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryViewModel
import com.example.mytrainingpal.ui.theme.Orange
import com.example.mytrainingpal.ui.theme.Red
import com.example.mytrainingpal.util.dateToLocalDate
import com.example.mytrainingpal.util.localDateToDate
import com.example.mytrainingpal.util.todayDate
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.time.LocalDateTime


@Composable
fun CalendarScreen(
    navController: NavController,
    workoutEntryViewModel: WorkoutEntryViewModel,
    musclePainEntryViewModel: MusclePainEntryViewModel
) {
    TabScreen(
        tabContent = { CalendarScreenContent(workoutEntryViewModel, musclePainEntryViewModel) },
        topBarIcon = Screen.CalendarMain.icon,
        topBarTitle = Screen.CalendarMain.label,
        navController = navController
    )
}

@Composable
fun CalendarScreenContent(
    workoutEntryViewModel: WorkoutEntryViewModel,
    musclePainEntryViewModel: MusclePainEntryViewModel
) {
    val allWorkouts by workoutEntryViewModel.allWorkouts.observeAsState(listOf())
    val allMusclePainEntries by musclePainEntryViewModel.allMusclePainEntries.observeAsState(listOf())
    val sortedWorkouts = allWorkouts.sortedBy { it.date }.reversed().toMutableList()
    val sortedMusclePainEntries =
        remember { allMusclePainEntries.sortedBy { it.date } }.reversed().toMutableList()
    val listEntries: MutableList<Pair<WorkoutEntry?, MusclePainEntry?>> = mutableListOf()

    if (sortedWorkouts.isNotEmpty() || sortedMusclePainEntries.isNotEmpty()) {
        var firstEntryDate = todayDate()

        if (sortedWorkouts.isEmpty()) {
            firstEntryDate = sortedMusclePainEntries.last().date
        } else if (sortedMusclePainEntries.isEmpty()) {
            firstEntryDate = sortedWorkouts.last().date
        } else {
            if (sortedWorkouts.last().date.before(sortedMusclePainEntries.last().date)) {
                firstEntryDate = sortedWorkouts.last().date
            } else if (sortedMusclePainEntries.last().date.before(sortedWorkouts.last().date)) {
                firstEntryDate = sortedMusclePainEntries.last().date
            }
        }

        // use LocalDateTime to be able to subtract days
        var currentDateTime = LocalDateTime.now()
        while (!localDateToDate(currentDateTime).before(firstEntryDate)) {
            if (sortedWorkouts.firstOrNull()?.date == localDateToDate(currentDateTime)
                && sortedMusclePainEntries.firstOrNull()?.date == localDateToDate(currentDateTime)
            ) {
                listEntries.add(Pair(sortedWorkouts.first(), sortedMusclePainEntries.first()))
                sortedWorkouts.removeFirst()
                sortedMusclePainEntries.removeFirst()
            } else if (sortedWorkouts.firstOrNull()?.date == localDateToDate(currentDateTime)) {
                listEntries.add(Pair(sortedWorkouts.first(), null))
                sortedWorkouts.removeFirst()
            } else if (sortedMusclePainEntries.firstOrNull()?.date
                == localDateToDate(currentDateTime)
            ) {
                listEntries.add(Pair(null, sortedMusclePainEntries.first()))
                sortedMusclePainEntries.removeFirst()
            } else {
                // no entry that day
            }
            currentDateTime = currentDateTime.minusDays(1)
        }
    }

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
                if (allWorkouts.find { day.date == dateToLocalDate(it.date) } != null) {
                    color = Red
                } else if (allMusclePainEntries.find { day.date == dateToLocalDate(it.date) } != null) {
                    color = Orange
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
        if (allWorkouts.isNotEmpty() || allMusclePainEntries.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                listEntries.forEach { (workout, musclePainEntry) ->
                    if (workout != null && musclePainEntry != null) {
                        item {
                            CalendarListWidget(
                                workout.date,
                                wasWorkout = true,
                                wasMusclePain = true
                            )
                        }
                    } else if (workout == null && musclePainEntry != null) {
                        item {
                            CalendarListWidget(
                                musclePainEntry.date,
                                wasWorkout = false,
                                wasMusclePain = true
                            )
                        }
                    } else if (workout != null) {
                        item {
                            CalendarListWidget(
                                workout.date,
                                wasWorkout = true,
                                wasMusclePain = false
                            )
                        }
                    }

                }
            }
        } else {
            Text("Still loading exer")
        }
    }
}