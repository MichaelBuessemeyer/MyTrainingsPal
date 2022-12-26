package com.example.mytrainingpal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomNumberInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    postText: String? = null,
    slimSize: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.background,
    possibleValues: List<Int> = listOf(10, 15, 20, 30, 45, 60, 90)
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val topBottomPadding = if (slimSize) 4.dp else 8.dp
    val inputWidth = if (slimSize) 50.dp else 80.dp
    val maxChars = if (slimSize) 2 else 5
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        SlimTextInput(
            value = value.toString(),
            onValueChange = { value ->
                try {
                    onValueChange(value.toInt())
                } catch (
                    _: NumberFormatException
                ) {
                }
            },
            modifier = modifier
                .width(inputWidth)
                .background(color = backgroundColor, shape = RoundedCornerShape(8.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            topBottomPadding = topBottomPadding,
            maxChars = maxChars
        )
        IconButton(onClick = { setShowDialog(true) }, modifier = Modifier.padding(0.dp)) {
            // TODO: open numberSlider on click. And make this an own component to be reusable
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary
            )
        }
        postText?.let { Text(it) }
    }
    NumberPickerDialog(
        showDialog,
        setShowDialog,
        value = value,
        onValueChange = onValueChange,
        possibleValues = possibleValues
    )
}
