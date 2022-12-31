package com.example.mytrainingpal.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytrainingpal.components.CustomNumberInput
import com.example.mytrainingpal.components.GifImage
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentExercise(
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    currentExerciseIndex: Int,
    //currentSetIndex: Int,
    goToBreak: () -> Unit,
    ){

    var currentExercise : Exercise = exerciseList[currentExerciseIndex].first
    //var nextExercise: Exercise = exerciseList[currentExerciseIndex + 1].first
    //var currentSetIndexDisplay: Int = currentSetIndex
    var currentExerciseIndexDisplay = currentExerciseIndex + 1
    var totalExercisesInList : Int = exerciseList.size
    //var totalSetsforExerciseInList: Int = exerciseList[currentExerciseIndex].second.sets

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
         Text(text = "Exercise $currentExerciseIndexDisplay/$totalExercisesInList", fontSize = 15.sp)
         Text(text = currentExercise.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
         GifImage(currentExercise.pathToGif, 1500)
         Text(text = "Link to youtube video that should be the instructions")
         CustomNumberInput(
             value = 1,
             onValueChange = { },
             possibleValues = (1..30).toList()
         )
         //Text(text = "Set $currentSetIndexDisplay/$totalSetsforExerciseInList", fontSize = 15.sp)
         LinearProgressIndicator(
             progress = currentExerciseIndex / totalExercisesInList.toFloat(),
             backgroundColor = MaterialTheme.colors.primary,
             color = MaterialTheme.colors.secondary,
             modifier = Modifier
                 .fillMaxWidth()
         )
         // TODO: Make the following a composable with parameters:
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





