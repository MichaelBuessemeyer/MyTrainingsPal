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
fun LastTrainingStatCard(thisTraining: Boolean = true) {
    val date = "10.02.22" //TODO: get everything from database
    var thisOrLastString = "This"
    if (!thisTraining) thisOrLastString = "Last"
    WidgetCard(hasBorder = false) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "$thisOrLastString Training:")
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    StatRow(
                        title = "Date",
                        stat = date,
                        imageVector = Icons.Default.CalendarToday,
                        showTitle = false,

                        )
                    //TODO: add other records
                }
            }
        }
    }
}