package com.example.mytrainingpal.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.chargemap.compose.numberpicker.ListItemPicker

@Composable
fun NumberPickerDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    value: Int,
    onValueChange: (Int) -> Unit,
    possibleValues: List<Int> = listOf(10, 15, 20, 30, 45, 60, 90)
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
                    value = value,
                    onValueChange = onValueChange,
                    list = possibleValues,
                    dividersColor = MaterialTheme.colors.primary,
                )
            }
        }
    }
}