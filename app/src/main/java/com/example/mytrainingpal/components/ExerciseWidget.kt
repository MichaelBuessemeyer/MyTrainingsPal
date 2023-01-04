package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytrainingpal.model.entities.Exercise

@Composable
fun   ExerciseWidget(
    exercise: Exercise,
    weight: Int,
    reps: Int,
    sets: Int,
    onRepsChanged: (Int) -> Unit,
    onSetsChanged: (Int) -> Unit
) {
    WidgetCard(hasBorder = false) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                val gifWithInPxl = LocalDensity.current.run { 80.dp.toPx() }.toInt()
                GifImage(gifPath = exercise.pathToGif, size = gifWithInPxl)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(exercise.name)
                Text("Weight: $weight")
                Text("TODO: Add see instructions", fontSize = 4.sp)
            }
            Spacer(modifier = Modifier.weight(1.0F))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text("Reps", textAlign = TextAlign.Center) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text("Sets", textAlign = TextAlign.Center) }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                CustomNumberInput(
                    value = reps,
                    onValueChange = onRepsChanged,
                    possibleValues = (1..30).toList()
                )
                CustomNumberInput(
                    value = sets,
                    onValueChange = onSetsChanged,
                    possibleValues = (1..20).toList()
                )
            }
        }
    }
}