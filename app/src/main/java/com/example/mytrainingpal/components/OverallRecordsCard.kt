package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OverallRecordsCard() {
    val daysExercised = 100 //TODO: get from database
    WidgetCard {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "Your Records:")
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    StatRow(
                        title = "Days Exercised",
                        stat = daysExercised,
                        imageVector = Icons.Default.CalendarToday
                    )
                    //TODO: add other records
                }
            }
        }
    }
}