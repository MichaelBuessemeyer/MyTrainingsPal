package com.example.mytrainingpal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnterPainPrompt(navigateToMusclePain: () -> Unit) {

// Card Reference: https://foso.github.io/Jetpack-Compose-Playground/material/card/
    WidgetCard(true){
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Please Enter Your Sore Muscles",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    // weight otherwise Icon is not displayed
                    modifier = Modifier.weight(1f),
                )
                IconButton(
                    onClick = { navigateToMusclePain() }
                ){
                    Icon(
                        Icons.Default.Edit, contentDescription = "Edit Muscle Pain",
                        tint =  MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}