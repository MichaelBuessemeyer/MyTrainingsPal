package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.model.entities.Exercise

@Composable
fun ExerciseWidget(
    exercise: Exercise,
    weight: Int,
    reps: Int,
    sets: Int,
    onRepsChanged: (Int) -> Unit,
    onSetsChanged: (Int) -> Unit
) {
    WidgetCard(hasBorder = false) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                val gifWithInPxl = LocalDensity.current.run { 80.dp.toPx() }.toInt()
                GifImage(gifPath = exercise.pathToGif, size = gifWithInPxl)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(2f)
            ) {
                Text(
                    exercise.name,
                    maxLines = 3,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text("Weight: $weight", textAlign = TextAlign.Center)
            }
            Spacer(modifier = Modifier.weight(.15f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(.5f)
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
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
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