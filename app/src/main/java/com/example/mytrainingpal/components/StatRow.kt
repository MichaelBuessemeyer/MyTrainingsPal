package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun StatRow(title: String, stat: Int, imageVector: ImageVector, showTitle: Boolean = true) {
    Row {
        Icon(
            imageVector = imageVector,
            contentDescription = ""
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
            contentDescription = ""
        )
        if (showTitle) Text(text = "$stat $title")
        else Text(text = stat)
    }
}