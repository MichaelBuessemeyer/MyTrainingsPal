package com.example.mytrainingpal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentExercise(
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    currentExerciseIndex: Int,
    currentExerciseSet: Int,
    totalSets: Int,
    currentSet: Int,
    goToBreak: () -> Unit,
) {
    // exercises that make up an exercise screen on a list size 3: currentExerciseCounter 0,1,
    var currentExercise: Exercise = exerciseList[currentExerciseIndex].first

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                goToBreak()
            })
            {
                Icon(
                    imageVector = Icons.Default.FastForward,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Start Training"
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(4F, true)
                    .fillMaxSize(),

                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Exercise ${currentExerciseIndex + 1}/${exerciseList.size}",
                        fontSize = 15.sp
                    )
                    Text(
                        text = currentExercise.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    GifImage(currentExercise.pathToGif, 300)
                    val dialogShowing = remember { mutableStateOf(false)  }
                    if(dialogShowing.value){
                        CustomDialog(dialogShowing.value)
                    }
                    ClickableText(
                        text = AnnotatedString("See Instructions on Youtube") ,
                        onClick = {
                            dialogShowing.value = true
                        })
                    IncreaseDecreaseNumberInput(
                        value = exerciseList[currentExerciseIndex].second.reps,
                        onValueChange = {}
                    )
                    Text(
                        text = "Set ${currentExerciseSet + 1}/${exerciseList[currentExerciseIndex].second.sets}",
                        fontSize = 15.sp
                    )
                    LinearProgressIndicator(
                        progress = currentSet / totalSets.toFloat(),
                        backgroundColor = MaterialTheme.colors.primary,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
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






