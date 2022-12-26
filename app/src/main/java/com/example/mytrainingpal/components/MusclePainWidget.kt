package com.example.mytrainingpal.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.mytrainingpal.components.svgs.getBodyMusclesWithPain
import com.example.mytrainingpal.model.MusclePainEntryMapConstants
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.ui.theme.Orange
import com.example.mytrainingpal.ui.theme.Red


private val muscleBounds = listOf(
    // All front muscles
    Triple(Pair(0.165, 0.2575), Pair(0.095, 0.15), "Right Neck"),
    Triple(Pair(0.2575, 0.35), Pair(0.095, 0.15), "Left Neck"),
    Triple(Pair(0.1, 0.174), Pair(0.146, 0.23), "Right Deltoids"),
    Triple(Pair(0.35, 0.44), Pair(0.146, 0.23), "Left Deltoids"),
    Triple(Pair(0.2, 0.28), Pair(0.16, 0.26), "Right Pectoralis"),
    Triple(Pair(0.28, 0.34), Pair(0.16, 0.26), "Left Pectoralis"),
    Triple(Pair(0.124, 0.16), Pair(0.23, 0.35), "Right Biceps"),
    Triple(Pair(0.34, 0.43), Pair(0.23, 0.35), "Left Biceps"),
    Triple(Pair(0.06, 0.15), Pair(0.35, 0.455), "Right Forearm"),
    Triple(Pair(0.4, 0.47), Pair(0.35, 0.455), "Left Forearm"),
    Triple(Pair(0.03, 0.14), Pair(0.455, 0.525), "Right Wrist"),
    Triple(Pair(0.4, 0.48), Pair(0.455, 0.525), "Left Wrist"),
    Triple(Pair(0.224, 0.278), Pair(0.26, 0.44), "Abdominals"),
    Triple(Pair(0.16, 0.224), Pair(0.26, 0.43), "Right Obliques"),
    Triple(Pair(0.278, 0.34), Pair(0.26, 0.43), "Left Obliques"),
    Triple(Pair(0.14, 0.2), Pair(0.43, 0.5), "Right Hip"),
    Triple(Pair(0.33, 0.4), Pair(0.43, 0.5), "Left Hip"),
    Triple(Pair(0.22, 0.25), Pair(0.44, 0.59), "Right Adductors"),
    Triple(Pair(0.25, 0.305), Pair(0.44, 0.59), "Left Adductors"),
    Triple(Pair(0.12, 0.247), Pair(0.5, 0.66), "Right Quadriceps"),
    Triple(Pair(0.26, 0.4), Pair(0.5, 0.66), "Left Quadriceps"),
    Triple(Pair(0.14, 0.24), Pair(0.66, 0.753), "Right Knee"),
    Triple(Pair(0.27, 0.38), Pair(0.66, 0.753), "Left Knee"),
    Triple(Pair(0.14, 0.24), Pair(0.753, 0.87), "Right Shin"),
    Triple(Pair(0.27, 0.38), Pair(0.753, 0.87), "Left Shin"),
    Triple(Pair(0.14, 0.25), Pair(0.87, 0.98), "Right Foot front"),
    Triple(Pair(0.25, 0.38), Pair(0.87, 0.98), "Left Foot front"),
)

// Circle taken from https://agarasul.medium.com/shapes-in-jetpack-compose-de740f5507f2
// by Rasul Aghakishiyev
@Composable
fun CircleShape(color: Color) {
    Canvas(modifier = Modifier.size(20.dp), onDraw = {
        val size = 20.dp.toPx()
        drawCircle(
            color = color,
            radius = size / 2f
        )
    })
}

@Composable
fun LegendEntry(text: String, color: Color) {
    CircleShape(color)
    Text(text)
}

@Composable
fun MusclePainWidget(
    navigateToMusclePain: () -> Unit = {},
    addSoreMuscle: (Pair<Muscle, Long>) -> Unit = {},
    setSoreMuscle: (index: Int, Pair<Muscle, Long>) -> Unit = { _, _ -> },
    removeSoreMuscle: (index: Int) -> Unit = {},
    editable: Boolean = false,
    showEditButton: Boolean = false,
    soreMuscles: MutableList<Pair<Muscle, Long>>
) {
    Log.d(
        "MusclePainWidget",
        "------------------------------------------------------- soreMuscles---------"
    )
    for (muscle in soreMuscles) {
        Log.d("MusclePainWidget", "muscle = ${muscle.first.name}, soreness = ${muscle.second}")
    }
    val painter = rememberVectorPainter(image = getBodyMusclesWithPain(soreMuscles))
    val size = remember { mutableStateOf(IntSize.Zero) }
    val painWasAlreadyEntered = true // TODO: Get this from the database
    var title = "Your sore muscles:"
    if (!painWasAlreadyEntered) {
        title = "Guessed sore muscles:"
    }
    fun handleMuscleTab(offset: Offset) {
        val relativeX: Float = offset.x / size.value.width
        val relativeY: Float = offset.y / size.value.height

        for ((xVals, yVals, muscleName) in muscleBounds) {
            if (xVals.first < relativeX && relativeX < xVals.second
                && yVals.first < relativeY && relativeY < yVals.second
            ) {
                Log.d("Tabbed Muscle", "Tabbed at muscle $muscleName")
                val index = soreMuscles.indexOfFirst { entry -> entry.first.name == muscleName }
                if (index > -1) {
                    when (soreMuscles[index].second) {
                        MusclePainEntryMapConstants.MODERATE_PAIN -> setSoreMuscle(
                            index,
                            Pair(soreMuscles[index].first, MusclePainEntryMapConstants.SEVERE_PAIN)
                        )
                        MusclePainEntryMapConstants.SEVERE_PAIN -> removeSoreMuscle(index)
                        else -> { // Note the block
                            print("Invalid soreness entry detected.")
                        }
                    }
                } else {
                    addSoreMuscle(
                        Pair(
                            Muscle(name = muscleName),
                            MusclePainEntryMapConstants.MODERATE_PAIN
                        )
                    )
                }
                return
            }
        }
    }
    WidgetCard {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(title, modifier = Modifier.align(Alignment.Start))
            // Getting size of the image during runtime with the help of the following post:
            // https://stackoverflow.com/questions/66440731/how-to-get-the-size-of-a-composable-during-runtime
            Box(Modifier.onGloballyPositioned { coordinates ->
                size.value = coordinates.size
            }) {
                Image(
                    painter = painter,
                    contentDescription = "Body Muscle SVG",
                    // Getting Tab position idea from
                    // https://stackoverflow.com/questions/70316606/jetpack-compose-get-click-position-in-custom-layout
                    Modifier.pointerInput(Unit) {
                        detectTapGestures { offset ->
                            if (editable) {
                                handleMuscleTab(offset)
                            }
                            Log.d(
                                "Tab Position",
                                "Tabbed at: $offset with size ${size.value} with relative x: ${offset.x / size.value.width}, y: ${offset.y / size.value.height}"
                            )
                        }
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                LegendEntry("Not Sore", Color.Black)
                LegendEntry("Light Sore", Orange)
                LegendEntry("Very Sore", Red)
            }
            if (showEditButton) {
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
}