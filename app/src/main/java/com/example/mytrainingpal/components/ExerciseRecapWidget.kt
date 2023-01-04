package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.model.entities.Exercise

@Composable
fun ExerciseRecapWidget(
    exercise: Exercise,
    weight: Int,
    reps: String,
    sets: Int,
){
    WidgetCard(hasBorder = false) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {

            // This column is meant to contain the image at some point
            /*Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                val gifWithInPxl = LocalDensity.current.run { 80.dp.toPx() }.toInt()
                GifImage(gifPath = exercise.pathToGif, size = gifWithInPxl)
            }*/

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(exercise.name)
                Text("Weight: $weight")
            }
            Spacer(modifier = Modifier.weight(1.0F))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text("Reps:", textAlign = TextAlign.Center) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text("Sets:", textAlign = TextAlign.Center) }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text(reps , textAlign = TextAlign.Center) }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(48.dp)
                ) { Text(sets.toString(), textAlign = TextAlign.Center) }
            }
        }
    }
}