package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.CustomNumberInput
import com.example.mytrainingpal.components.TabScreen


@Composable
fun TrainingScreen(navController: NavController) {
    var minutes by remember { mutableStateOf(20) }
    TabScreen(tabContent = {
        MainTrainingScreenContent(
            minutes = minutes,
            updateMinutes = { inputValue: Int -> minutes = inputValue })
    }, navController = navController)
}

@Composable
fun MainTrainingScreenContent(minutes: Int, updateMinutes: (Int) -> Unit) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .blur(if (showDialog) 5.dp else 0.dp)
    ) {
        Text(
            text = "How much time do you have?",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
        CustomNumberInput(
            value = minutes, onValueChange = updateMinutes, postText = "min", slimSize = false,
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}