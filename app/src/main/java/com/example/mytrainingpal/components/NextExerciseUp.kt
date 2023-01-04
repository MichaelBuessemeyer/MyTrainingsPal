package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun nextExerciseUp(
    gifPath: String,
    exerciseName: String,
    suggestedRepsForThatExercise: Int,
    setsLeft: Int
) {

    Column {
        Text(text = "Up next:")
        // gifs saved under R.drawable.<gifName> (added through the GUI Resource Manager)
        GifImage(gifPath, 100)
    }
    Text(
        text = exerciseName,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Column {
        Text(text = "$suggestedRepsForThatExercise REPS")
        Text(text = "$setsLeft SETS")
    }
}