package com.example.mytrainingpal.components

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.util.ExerciseDetails

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CurrentExercise(
    exerciseList: MutableList<Pair<Exercise, ExerciseDetails>>,
    currentExerciseIndex: Int,
    currentExerciseSetCounter: Int, // if an exercise has 4 sets: 0, 1, 2, 3
    totalSets: Int,
    currentSet: Int,
    goToBreak: (Int) -> Unit,
) {
    // exercises that make up an exercise screen on a list size 3: currentExerciseCounter 0,1,
    val currentExercise: Exercise = exerciseList[currentExerciseIndex].first
    var myReps by remember { mutableStateOf(exerciseList[currentExerciseIndex].second.reps.split(",")[0].toInt()) }

    Scaffold(backgroundColor = MaterialTheme.colors.background, floatingActionButton = {
        FloatingActionButton(onClick = {
            goToBreak(myReps)
        }) {
            Icon(
                imageVector = Icons.Default.FastForward,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = "Start Training"
            )
        }
    }) {

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
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Exercise ${currentExerciseIndex + 1}/${exerciseList.size}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    WidgetCard(hasBorder = false) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text(
                                text = currentExercise.name,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(10.dp)
                            )
                            GifImage(currentExercise.pathToGif, 500)

                            // Taken from Thracian's answer on Github: https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text
                            val annotatedLinkString: AnnotatedString = buildAnnotatedString {

                                val str = "See Instructions on Youtube"
                                val startIndex = str.indexOf("See")
                                val endIndex = startIndex + 27
                                append(str)
                                addStyle(
                                    style = SpanStyle(
                                        color = Color(0xff64B5F6),
                                        fontSize = 18.sp,
                                        textDecoration = TextDecoration.Underline
                                    ), start = startIndex, end = endIndex
                                )
                                // attach a string annotation that stores a URL to the text "link"
                                addStringAnnotation(
                                    tag = "URL",
                                    annotation = "https://www.youtube.com/watch?v=1Tq3QdYUuHs",
                                    start = startIndex,
                                    end = endIndex
                                )
                            }
                            // comment from Github: "UriHandler parse and opens URI inside AnnotatedString Item in Browse"
                            val uriHandler = LocalUriHandler.current
                            // comment from Github: "Clickable text returns position of text that is clicked in onClick callback"
                            ClickableText(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                                text = annotatedLinkString,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                ),
                                onClick = {
                                    annotatedLinkString.getStringAnnotations("URL", it, it)
                                        .firstOrNull()?.let { stringAnnotation ->
                                            uriHandler.openUri(stringAnnotation.item)
                                        }
                                })

                        }
                    }

                    val interactionSource = remember { MutableInteractionSource() }

                    Row {
                        IconButton(onClick = {
                            myReps--
                        }) {
                            Icon(
                                imageVector = Icons.Default.RemoveCircle,
                                contentDescription = "Decrease number of reps",
                                tint = MaterialTheme.colors.secondary
                            )
                        }

                        BasicTextField(
                            value = myReps.toString(),
                            onValueChange = {
                                try {
                                    myReps = it.toInt()
                                } catch (
                                    _: NumberFormatException
                                ) {
                                }
                            },
                            modifier = Modifier
                                .width(80.dp)
                                .background(
                                    color = MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                            interactionSource = interactionSource,
                        ) {
                            TextFieldDefaults.TextFieldDecorationBox(
                                value = myReps.toString(),
                                enabled = true,
                                interactionSource = interactionSource,
                                innerTextField = it,
                                singleLine = true,
                                visualTransformation = VisualTransformation.None,
                                // keep horizontal paddings but change the vertical
                                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                                    top = 8.dp, bottom = 8.dp
                                )
                            )

                        }


                        IconButton(onClick = {
                            myReps++
                        }) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Increase number of reps",
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                    // --------------------

                    Text(
                        text = "Set ${currentExerciseSetCounter + 1}/${exerciseList[currentExerciseIndex].second.sets}",
                        fontSize = 15.sp
                    )
                    LinearProgressIndicator(
                        progress = currentSet / totalSets.toFloat(),
                        backgroundColor = MaterialTheme.colors.primary,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .weight(1F, true)
                    .fillMaxSize(),
            ) {
                if (currentExerciseSetCounter + 1 == exerciseList[currentExerciseIndex].second.sets && currentExerciseIndex + 1 == exerciseList.size) {
                    Text(
                        text = "You are almost done!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (currentExerciseSetCounter + 1 == exerciseList[currentExerciseIndex].second.sets) {
                    nextExerciseUp(
                        gifPath = exerciseList[currentExerciseIndex + 1].first.pathToGif,
                        exerciseName = exerciseList[currentExerciseIndex + 1].first.name,
                        suggestedRepsForThatExercise = exerciseList[currentExerciseIndex].second.reps.split(
                            ","
                        )[0].toInt(),
                        setsLeft = exerciseList[currentExerciseIndex].second.sets
                    )
                } else {
                    nextExerciseUp(
                        gifPath = exerciseList[currentExerciseIndex].first.pathToGif,
                        exerciseName = exerciseList[currentExerciseIndex].first.name,
                        suggestedRepsForThatExercise = exerciseList[currentExerciseIndex].second.reps.split(
                            ","
                        )[0].toInt(),
                        setsLeft = exerciseList[currentExerciseIndex].second.sets - (currentExerciseSetCounter + 1)
                    )
                }
            }
        }
    }
}








