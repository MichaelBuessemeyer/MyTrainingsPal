package com.example.mytrainingpal.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextIconWidget(text: String, imageVector: ImageVector) {
    WidgetCard(hasBorder = false) {
        Row(modifier = Modifier.wrapContentWidth()) {
            Text(text = text)
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = imageVector,
                contentDescription = ""
            )
        }
    }
}
