package com.example.mytrainingpal.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.components.myiconpack.ShoulderPainIcon
import java.util.*

@Composable
fun CalendarListWidget(
    date: Date,
    wasWorkout: Boolean,
    wasMusclePain: Boolean
) {

    WidgetCard(hasBorder = false) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            Text(
                text = SimpleDateFormat("dd/MM", Locale.US).format(date),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )

            if (wasWorkout)
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "exercise icon",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

            if (wasMusclePain)
                Icon(
                    imageVector = ShoulderPainIcon(),
                    contentDescription = "muscle pain icon",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

        }
    }
}