package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoNotDisturb
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.time.LocalDateTime
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import com.example.mytrainingpal.model.view_models.ExerciseViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.util.*
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.time.YearMonth
import java.util.*


@Composable
fun CalendarScreen(
    navController: NavController,
    workoutEntryViewModel: WorkoutEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel

) {
    TabScreen(
        tabContent = { CalendarScreenContent(workoutEntryViewModel, musclePainEntryMapViewModel) },
        topBarIcon = Screen.CalendarMain.icon,
        topBarTitle = Screen.CalendarMain.label,
        navController = navController
    )
}

@Composable
fun CalendarScreenContent(workoutEntryViewModel: WorkoutEntryViewModel, musclePainEntryMapViewModel: MusclePainEntryMapViewModel) {
    val calendarState = rememberCalendarState()
    val allWorkouts by workoutEntryViewModel.allWorkouts.observeAsState(listOf())
    val allMusclePainEntriesWithMuscles by musclePainEntryMapViewModel.allMusclePainEntriesWithMuscles.observeAsState(listOf())
    val dayEntryMap: Map<Date, CalendarEntry> = remember(allMusclePainEntriesWithMuscles, allWorkouts){
        val calendarMap: MutableMap<Date, CalendarEntry> = mutableMapOf()
        for(musclePainEntry in allMusclePainEntriesWithMuscles){
            val date = musclePainEntry.musclePainEntry.date
            calendarMap[date] = CalendarEntry(date, musclePainEntry)
        }
        for(workoutEntry in allWorkouts){
            val date = workoutEntry.date
            val existingMusclePainEntry = calendarMap[date]?.musclePainEntry
            calendarMap[date] = CalendarEntry(date, musclePainEntry = existingMusclePainEntry, workoutEntry)
        }
        calendarMap
    }
    val monthsSortedEntries = remember(calendarState.monthState.currentMonth, allMusclePainEntriesWithMuscles) {
        val sortedEntries: MutableList<CalendarEntry> = mutableListOf()
        for((date, entry) in dayEntryMap){
            val cal = calendarFromDate(date)
            val calendarsYearMonth = calendarState.monthState.currentMonth
            if((calendarsYearMonth.month.value - 1) == cal.get(Calendar.MONTH) && calendarsYearMonth.year == cal.get(Calendar.YEAR)){
                sortedEntries.add(entry)
            }
        }
        sortedEntries.sortBy { it.date }
        sortedEntries
    }
    Column {
        StaticCalendar(
            calendarState = calendarState,
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
                if (dayEntryMap.containsKey(localDateToJavaDate(day.date))) {
                    color = MaterialTheme.colors.secondary
                }

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = color, shape = RoundedCornerShape(4.dp))
                        .aspectRatio(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center

                ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                }
            })
        if (monthsSortedEntries.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(monthsSortedEntries) { calendarEntry ->
                    CalendarListWidget(
                        calendarEntry,
                    )
                }
            }
        } else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DoNotDisturb,
                        contentDescription = "crossed circle icon",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Text("No entries for this month.")
            }
        }
    }
}