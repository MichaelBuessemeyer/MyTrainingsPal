package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.WidgetCard
import io.github.boguszpawlowski.composecalendar.StaticCalendar


@Composable
fun CalendarScreen(navController: NavController) {
    TabScreen(tabContent = { CalendarScreenContent() }, navController = navController)
}

@Composable
fun CalendarScreenContent() {
    val placeholderNumbers = List(100) { it }
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
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(placeholderNumbers) { number ->
                Greeting(name = "$number")
            }
        }
    }
}