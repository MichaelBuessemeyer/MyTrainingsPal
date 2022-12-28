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
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.WidgetCard
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import com.example.mytrainingpal.model.view_models.ExerciseViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.util.calendarFromDate
import com.example.mytrainingpal.util.localDateToJavaDate
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.time.YearMonth
import java.util.*


@Composable
fun CalendarScreen(
    navController: NavController,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel
) {
    TabScreen(
        tabContent = { CalendarScreenContent(musclePainEntryMapViewModel) },
        topBarIcon = Screen.CalendarMain.icon,
        topBarTitle = Screen.CalendarMain.label,
        navController = navController
    )
}

data class CalendarEntry(
    val musclePainEntry: MusclePainEntryWithMuscles,
    val date: Date
)

@Composable
fun CalendarScreenContent(musclePainEntryMapViewModel: MusclePainEntryMapViewModel) {
    val calendarState = rememberCalendarState()
    val allMusclePainEntriesWithMuscles by musclePainEntryMapViewModel.allMusclePainEntriesWithMuscles.observeAsState(listOf())
    val dayEntryMap: Map<Date, CalendarEntry> = remember(allMusclePainEntriesWithMuscles){
        val calendarMap: MutableMap<Date, CalendarEntry> = mutableMapOf()
        for(entry in allMusclePainEntriesWithMuscles){
            calendarMap[entry.musclePainEntry.date] = CalendarEntry(entry, entry.musclePainEntry.date)
        }
        calendarMap
    }
    val monthsSortedEntries = remember(calendarState, allMusclePainEntriesWithMuscles) {
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
                        .fillMaxWidth()

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
                    Row{
                        val cal = calendarFromDate(calendarEntry.date)
                        val musclePainEntry = calendarEntry.musclePainEntry.musclePainEntry
                        Text("${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH)}", style = MaterialTheme.typography.h2)
                        Column {
                            Icon(
                                imageVector = Icons.Default.FitnessCenter,
                                tint = MaterialTheme.colors.onSecondary,
                                contentDescription = "Start Training",
                                modifier = Modifier.size(50.dp)
                            )
                            Text("")
                        }
                    }
                }
            }
        } else {
            Text("No entries this month.")
        }
    }
}