package com.example.mytrainingpal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WidgetCard(hasBorder: Boolean, content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        border =
        if(hasBorder){
            BorderStroke(2.dp, MaterialTheme.colors.secondary)
        } else {
            BorderStroke(2.dp, MaterialTheme.colors.background)
        }
    ) {
        content()
    }

}