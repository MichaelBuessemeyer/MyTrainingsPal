package com.example.mytrainingpal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.GifImage
import com.example.mytrainingpal.components.WidgetCard
import com.example.mytrainingpal.components.YoutubeDialog
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Break(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    currentExerciseIndex: Int,
    totalSets: Int,
    currentSet: Int
) {
    var currentExercise = currentExerciseIndex;
    var totalBreakTimeInSeconds: Int = 5

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column{
            Row(
                modifier = Modifier
                    .weight(4F, true)
                    .fillMaxSize(),

                ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Exercise ${currentExerciseIndex + 1} / ${exerciseList.size.toString()}"
                    )
                    Text(
                        text = "Break",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        BreakTimer(
                            totalTime = totalBreakTimeInSeconds * 1000L,
                            //handleColor = Color.Green,
                            inactiveBarColor = MaterialTheme.colors.primary,
                            activeBarColor = MaterialTheme.colors.secondary,
                            modifier = Modifier.size(180.dp)
                        )
                    }

                    LinearProgressIndicator(
                        progress = currentSet / totalSets.toFloat(),
                        backgroundColor = MaterialTheme.colors.primary,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    // -------------------------------------------------------------------------------------
                }

                }
                Row(
                    modifier = Modifier
                        .weight(1F, true)
                        .fillMaxSize(),

                    ){
                    // TODO: Make the following a composable with parameters:
                    if (currentExerciseIndex < exerciseList.size - 1) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)
                        ) {
                            Column {
                                Text(text = "Next Up:")
                                // gifs saved under R.drawable.<gifName> (added through the GUI Resource Manager)
                                GifImage(exerciseList[currentExerciseIndex].first.pathToGif, 100)
                            }
                            Text(
                                text = exerciseList[currentExerciseIndex].first.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Column {
                                Text(text = "XXXX REPS")
                                Text(text = "${totalSets.toString()} SETS")
                            }
                        }

                    } else {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)
                        ) {
                            Text(
                                text = "You are almost done!",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
}



// Timer composable adapted from Phillip Lackners' open source code available on Github:
// https://github.com/philipplackner/ComposeTimer/blob/master/app/src/main/java/com/plcoding/composetimer/MainActivity.kt

@Composable
fun BreakTimer(
    totalTime: Long,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 15.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    // percentage of the circle that is covered
    var value by remember {
        mutableStateOf(initialValue)
    }

    // time in milliseconds we are currently at
    var currentTime by remember {
        mutableStateOf(totalTime)
    }

    // this will make us have to star the timer manually
    var isTimerRunning by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .onSizeChanged {
                size = it
            }
    ) {

        Canvas(modifier = modifier) {
            drawArc(
                color = activeBarColor,
                startAngle = 180f,
                sweepAngle = -360f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx())
            )
            drawArc(
                color = inactiveBarColor,
                startAngle = 180f,
                sweepAngle = -360f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx())
            )

        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}











