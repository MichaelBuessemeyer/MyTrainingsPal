package com.example.mytrainingpal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mytrainingpal.components.GifImage
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

import kotlinx.coroutines.delay

// Timer composable adapted from Phillip Lackners' open source code available on Github:
// https://github.com/philipplackner/ComposeTimer/blob/master/app/src/main/java/com/plcoding/composetimer/MainActivity.kt


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Break(
    navController: NavController,
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    currentExerciseIndex: Int
) {
    var currentExercise: Int = 4;
    var totalExercises: Int = 10;
    var totalBreakTimeInSeconds: Int = 5

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Text(
                text = "Exercise $currentExerciseIndex / ${exerciseList.size.toString()}"
            )
            Text(
                text = "Break",
                fontSize = 25.sp,
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
                progress = currentExercise / totalExercises.toFloat(),
                backgroundColor = MaterialTheme.colors.primary,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(text = "Next Up:")
                    // gifs saved under R.drawable.<gifName> (added through the GUI Resource Manager)
                    GifImage("exercise_4", 200)
                }
                Text(
                    text = "Exercise Name",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Column {
                    Text(text = "20 REPS")
                    Text(text = "30 SETS")
                }
            }
        }
    }
}


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



