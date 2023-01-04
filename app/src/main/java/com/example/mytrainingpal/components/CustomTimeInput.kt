package com.example.mytrainingpal.components

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun CustomTimeInput(
    value: String,
    onValueChange: (String) -> Unit,
    postText: String? = null,
    backgroundColor: Color = MaterialTheme.colors.background
) {
    val topBottomPadding = 8.dp
    val inputWidth = 80.dp
    val maxChars = 5
    // https://medium.com/@daniel.atitienei/date-and-time-pickers-in-jetpack-compose-f641b1d72dd5
    val selectedTimeText: MutableState<String> = remember { mutableStateOf(value) }
    // Fetching current hour, and minute
    // TODO: error handling for invalid input format (not hh:mm)
    val hour = selectedTimeText.value.split(":")[0].toInt()
    val minute = selectedTimeText.value.split(":")[1].toInt()

    val timePicker = TimePickerDialog(
        LocalContext.current,
        { _, selectedHour: Int, selectedMinute: Int ->
            selectedTimeText.value = "$selectedHour:$selectedMinute"
            onValueChange(selectedTimeText.value)
        }, hour, minute, true
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        Text(value)
        IconButton(onClick = { timePicker.show() }, modifier = Modifier.padding(0.dp)) {
            // TODO: open numberSlider on click. And make this an own component to be reusable
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary
            )
        }
        postText?.let { Text(it) }
    }
}
