package com.example.mytrainingpal.components

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
import java.util.concurrent.TimeUnit

data class OverallStats(
    val workoutCount: Int,
    val maxDuration: Int,
    val totalDuration: Int,
    val totalWeightLifted: Int,
)

@Composable
fun OverallRecordsCard(workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel) {
    val allWorkoutEntriesWithExercises by workoutEntryExerciseMapViewModel.allWorkoutEntriesWithExercises.observeAsState(
        listOf()
    )
    val overallStats: OverallStats? = remember(allWorkoutEntriesWithExercises) {
        if (allWorkoutEntriesWithExercises.isNotEmpty()) {
            val workoutCount = allWorkoutEntriesWithExercises.size
            var maxDuration = 0
            var totalWorkoutDuration: Long = 0
            var totalWeightLifted = 0
            for (entry in allWorkoutEntriesWithExercises) {
                val (durationInMinutes, weightLifted) = entry.calculateStats()
                maxDuration = maxOf(maxDuration, durationInMinutes)
                totalWorkoutDuration += durationInMinutes
                totalWeightLifted += weightLifted
            }
            val totalWorkoutDurationInHours = TimeUnit.MINUTES.toHours(totalWorkoutDuration).toInt()
            OverallStats(workoutCount, maxDuration, totalWorkoutDurationInHours, totalWeightLifted)
        } else null
    }

    WidgetCard(hasBorder = false) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Your Records:")
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    StatRow(
                        title = "Workouts",
                        stat = overallStats?.workoutCount ?: 0,
                        imageVector = Icons.Default.CalendarToday
                    )
                    StatRow(
                        title = "kg lifted",
                        stat = overallStats?.totalWeightLifted ?: 0,
                        imageVector = Icons.Default.FitnessCenter
                    )
                    StatRow(
                        title = "min longest workout",
                        stat = overallStats?.maxDuration ?: 0,
                        imageVector = Icons.Default.AccessTime
                    )
                    StatRow(
                        title = "hours worked out",
                        stat = overallStats?.totalDuration ?: 0,
                        imageVector = Icons.Default.AccessTime
                    )
                }
            }
        }
    }
}