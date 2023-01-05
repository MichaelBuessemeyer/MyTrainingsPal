package com.example.mytrainingpal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.preferences.PreferencesConstants

@Composable
fun NotificationSetting(
    time: String,
    onTimeChange: (String) -> Unit,
    days: Set<String>,
    onDaysChange: (Set<String>) -> Unit,
) {
    val horizontalPaddingModifier = Modifier.padding(horizontal = 10.dp)
    var notificationDaysState by remember { mutableStateOf(days.toSet()) }
    remember(days) {
        notificationDaysState = days
        days
    }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = horizontalPaddingModifier) {
        Text(text = "Notify me on")
    }
    Row(
        verticalAlignment = Alignment.Top,
        modifier = horizontalPaddingModifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // TODO: make wrap in widget or something to make it prettier
        (0..6).forEach { index ->
            val isSelected = notificationDaysState.contains(PreferencesConstants.DAYS[index])
            val color = if (isSelected) {
                MaterialTheme.colors.secondary
            } else {
                Color.White
            }

            Box(modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    notificationDaysState = if (it) {
                        notificationDaysState + PreferencesConstants.DAYS[index]
                    } else {
                        notificationDaysState - PreferencesConstants.DAYS[index]
                    }
                    onDaysChange(notificationDaysState)
                }
            )) {
                Text(
                    text = PreferencesConstants.DAYS[index],
                    color = color,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(5.dp)
                )
            }
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = horizontalPaddingModifier) {
        Text(text = "at")
        Spacer(modifier = Modifier.size(12.dp))
        CustomTimeInput(
            value = time,
            onValueChange = onTimeChange,
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}
