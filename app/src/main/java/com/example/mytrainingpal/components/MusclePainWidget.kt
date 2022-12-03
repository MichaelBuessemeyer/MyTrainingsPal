package com.example.mytrainingpal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.R

@Composable
fun MusclePainWidget(navigateToMusclePain: () -> Unit = {}) {
    val painWasAlreadyEntered = true // TODO: Get this from the database
    var title = "Your sore muscles:"
    if (!painWasAlreadyEntered) {
        title = "Guessed sore muscles:"
    }
    WidgetCard {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(title, modifier = Modifier.align(Alignment.Start))
            Image(
                // TODO: Show the muscles that are sore (get last pain input from database)
                painterResource(id = R.drawable.body_muscles),
                contentDescription = "Body Muscle SVG",
            )
            Button(
                border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                onClick = { navigateToMusclePain() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.secondary
                ),
            ) {
                Text("Edit")
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Muscle Pain")
            }
        }
    }
}