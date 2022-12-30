package com.example.mytrainingpal.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.components.myiconpack.ShoulderPainIcon
import com.example.mytrainingpal.util.CalendarEntry
import java.util.*

@Composable
fun CalendarListWidget(
    calendarEntry: CalendarEntry
) {
    val soreMuscleCount = calendarEntry.musclePainEntry?.soreMusclesConnections?.size ?: 0
    val hasHighSoreness = soreMuscleCount > 20
    WidgetCard(hasBorder = false) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp).height(72.dp)) {
            Text(
                text = SimpleDateFormat("dd/MM", Locale.US).format(calendarEntry.date),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            calendarEntry.workout?.let { workout ->
                Column(modifier = Modifier.padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = "exercise icon",
                        tint = MaterialTheme.colors.onSecondary,
                        modifier = Modifier.size(50.dp)

                    )
                    Text("${workout.duration} mins.")
                }
            }

            calendarEntry.musclePainEntry?.let { musclePainWithMuscles ->
                Column(modifier = Modifier.padding(horizontal = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = ShoulderPainIcon(),
                        contentDescription = "muscle pain icon",
                        modifier = Modifier.size(50.dp)
                    )
                    Text(if(hasHighSoreness) "High Soreness" else "Low Soreness")
                }
            }
        }
    }
}