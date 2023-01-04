package com.example.mytrainingpal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun UserNameWithSettings(name: String, navigateToSettings: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, style = MaterialTheme.typography.h2)
        IconButton(onClick = navigateToSettings) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "",
                tint = MaterialTheme.colors.secondary
            )
        }
    }
}