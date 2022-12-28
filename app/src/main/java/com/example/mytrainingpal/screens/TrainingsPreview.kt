package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.StartExerciseFloatingButton
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.TextIconWidget
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.intermediate_entities.WorkoutEntryWithExercises
import com.example.mytrainingpal.model.view_models.ExerciseViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryExerciseMapViewModel
import com.example.mytrainingpal.model.view_models.WorkoutEntryViewModel
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.IntHolder
import com.example.mytrainingpal.util.todayDate


@Composable
fun TrainingsPreviewScreen(navController: NavController, duration: IntHolder, exercises: List<Pair<Exercise, ExerciseDetails>>, musclePainEntryViewModel: MusclePainEntryViewModel, exerciseViewModel: ExerciseViewModel, workoutEntryViewModel: WorkoutEntryViewModel, workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel) {
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)
    val workoutId = workoutEntryViewModel.insertWorkoutEntry(WorkoutEntry(date = todayDate(), startTime = "", endTime = "", comment = ""))
    val possibleExercises: List<Exercise> = exerciseViewModel.allExercises.value?.subList(0, 3) ?: listOf()
    val workoutExercises = remember { mutableStateListOf<WorkoutEntryWithExercises>()}

    TabScreen(
        tabContent = {
            TrainingsPreviewScreenContent(musclePain = todaysMusclePainEntry, workoutExercises)
        },
        topBarTitle = Screen.TrainingsPreview.label,
        topBarIcon = Screen.TrainingsPreview.icon,
        navController = navController,
        floatingActionButton =
        {
            FloatingActionButton(onClick = { navController.navigate(
                Screen.ScreenToggle.route
            ) }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Start Training"
                )
            }
        }
    )
}

@Composable
fun TrainingsPreviewScreenContent(musclePain: MusclePainEntry?, exercises: List<WorkoutEntryWithExercises>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        TextIconWidget(text = "Warm up", imageVector = Icons.Default.LocalFireDepartment)
        TextIconWidget(text = "Your Routine", imageVector = Icons.Default.FitnessCenter)
        TextIconWidget(text = "Cool down", imageVector = Icons.Default.AcUnit)

    }
}