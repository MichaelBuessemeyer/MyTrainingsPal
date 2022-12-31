package com.example.mytrainingpal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.mytrainingpal.model.entities.Exercise

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentExerciseScreen(exercise: String, currentSet: Int, totalSets: Int) {



    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

                Text(
                    text = exercise,
                    fontSize = 20.sp
                )

            Text(
                text = "current set: $currentSet/ total sets: $totalSets",
                fontSize = 15.sp
            )

        }
    }


}
