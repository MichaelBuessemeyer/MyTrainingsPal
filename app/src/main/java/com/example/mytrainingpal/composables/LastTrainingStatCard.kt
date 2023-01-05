package com.example.mytrainingpal.composables

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.model.view_models.WorkoutEntryExerciseMapViewModel
import com.example.mytrainingpal.util.TrainingStats

@Composable
fun LastTrainingStatCard(workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel) {
    val allWorkoutEntriesWithExercises by workoutEntryExerciseMapViewModel.allWorkoutEntriesWithExercises.observeAsState(
        listOf()
    )
    val lastTrainingStats: TrainingStats? = remember(allWorkoutEntriesWithExercises) {
        if (allWorkoutEntriesWithExercises.isNotEmpty()) {
            var entryWithMostRecentWorkout = allWorkoutEntriesWithExercises[0]
            for (entry in allWorkoutEntriesWithExercises) {
                if (entry.workoutEntry.date > entryWithMostRecentWorkout.workoutEntry.date) {
                    entryWithMostRecentWorkout = entry
                }
            }
            entryWithMostRecentWorkout.calculateStats()
        } else null
    }
    WidgetCard(hasBorder = false) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Last Training:")
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    StatRow(
                        title = "Date",
                        stat = if (lastTrainingStats != null) DateFormat.getDateInstance()
                            .format(lastTrainingStats.date) else "",
                        imageVector = Icons.Default.CalendarToday,
                        showTitle = false,

                        )
                    StatRow(
                        title = "kg lifted",
                        stat = lastTrainingStats?.totalWeightLifted ?: 0,
                        imageVector = Icons.Default.FitnessCenter
                    )
                    StatRow(
                        title = "min.",
                        stat = lastTrainingStats?.durationInMinutes ?: 0,
                        imageVector = Icons.Default.AccessTime
                    )
                }
            }
        }
    }
}