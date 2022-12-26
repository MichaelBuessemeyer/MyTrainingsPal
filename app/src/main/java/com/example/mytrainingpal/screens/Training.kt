package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen


@Composable
fun TrainingScreen(navController: NavController) {
    var minutes by remember { mutableStateOf(20) }
    TabScreen(
        tabContent = {
            MainTrainingScreenContent(
                minutes = minutes,
                updateMinutes = { inputValue: Int -> minutes = inputValue })
        },
        topBarTitle = Screen.TrainingMain.label,
        topBarIcon = Screen.TrainingMain.icon,
        navController = navController
    )
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = minutes.toString(),
                onValueChange = { value -> updateMinutes(value.toInt()) },
                modifier = Modifier.width(80.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            IconButton(onClick = { setShowDialog(true) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                    tint = MaterialTheme.colors.secondary
                )
            }
            Text(text = "min")
        }
    }
    NumberPickerDialog(showDialog, setShowDialog, minutes, updateMinutes)

}

@Composable
fun NumberPickerDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    minutes: Int,
    updateMinutes: (Int) -> Unit
) {
    if (showDialog) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = { setShowDialog(false) }
        ) {
            val dialogShape = RoundedCornerShape(8)
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background, dialogShape)
                    .border(1.dp, MaterialTheme.colors.secondary, dialogShape)
                    .padding(16.dp)
            ) {
                ListItemPicker(
                    value = minutes,
                    onValueChange = { value -> updateMinutes(value) },
                    list = listOf(10, 15, 20, 30, 45, 60, 90),
                    dividersColor = MaterialTheme.colors.primary,
                )
            }
        }
    }

}