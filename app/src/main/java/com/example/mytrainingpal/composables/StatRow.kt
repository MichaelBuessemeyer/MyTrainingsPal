package com.example.mytrainingpal.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun StatRow(title: String, stat: Int, imageVector: ImageVector, showTitle: Boolean = true) {
    Row {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.padding(end = 8.dp)
        )
        if (showTitle) Text(text = "$stat $title")
        else Text(text = "$stat")
    }
}

@Composable
fun StatRow(title: String, stat: String, imageVector: ImageVector, showTitle: Boolean = true) {
    Row {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.padding(end = 8.dp)

        )
        if (showTitle) Text(text = "$stat $title")
        else Text(text = stat)
    }
}