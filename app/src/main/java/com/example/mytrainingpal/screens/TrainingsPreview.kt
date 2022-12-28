package com.example.mytrainingpal.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.mytrainingpal.components.ExerciseWidget
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.components.TextIconWidget
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.view_models.*
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.IntHolder


@Composable
fun TrainingsPreviewScreen(
    navController: NavController,
    duration: IntHolder,
    exercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
    exerciseViewModel: ExerciseViewModel,
    exerciseMuscleMapViewModel: ExerciseMuscleMapViewModel,
    workoutEntryExerciseMapViewModel: WorkoutEntryExerciseMapViewModel
) {
    // get the current muscle pains
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)
    RememberFetchMusclePainEntryWithMuscles(
        todaysMusclePainEntry = todaysMusclePainEntry,
        musclePainEntryMapViewModel = musclePainEntryMapViewModel
    )
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    RememberAddingSoreMusclesToList(musclePainEntryMapViewModel, soreMuscles)

    val allExercises by exerciseMuscleMapViewModel.allExercisesWithMuscles.observeAsState(listOf())

    val unsoreMuscles: Set<Muscle> = soreMuscles.filter { it.second == 0L }.map { it.first }.toSet()
    val slightlySoreMuscles: Set<Muscle> =
        soreMuscles.filter { it.second == 1L }.map { it.first }.toSet()

    val unsoreExercises: MutableList<Exercise> = allExercises.filter { (_, muscles) ->
        muscles.intersect(soreMuscles).isEmpty() && muscles.intersect(slightlySoreMuscles).isEmpty()
    }.map { (exercise, _) -> exercise }.toMutableList()
    val slightlySoreExercises: MutableList<Exercise> = allExercises.filter { (_, muscles) ->
        muscles.intersect(soreMuscles).isEmpty() && muscles.intersect(slightlySoreMuscles)
            .isNotEmpty()
    }.map { (exercise, _) -> exercise }.toMutableList()

    // TODO: select exercises based on sore muscles
    // default: 1 set 1 min
    // pause zwischen den übungen: 30 sekunden
    // sets: 1,2,3 gespeichert ;)

    val defaultSets: Int = 3
    val defaultReps: Int = 10
    val defaultWeight: Int = 20
    val defaultDetails = ExerciseDetails(
        sets = defaultSets,
        reps = defaultReps,
        weight = defaultWeight
    )
    if (allExercises.isNotEmpty() && exercises.isEmpty()) {
        var currentDuration: Int = 0
        var overlap: Int = 0
        val selectedExercises: MutableList<Exercise> =
            unsoreExercises.asSequence().shuffled().take(duration.value / defaultSets).toMutableList()
        currentDuration += selectedExercises.size * defaultSets
        if (duration.value - currentDuration > defaultSets) {
            selectedExercises.addAll(
                slightlySoreExercises.asSequence().shuffled().take((duration.value - currentDuration) / defaultSets)
                    .toMutableList()
            )

        }
        overlap = duration.value - currentDuration

        // here we assume that after this step there is no oeverlap left
        for (exercise in selectedExercises) {
            if(overlap > 0) {
                exercises.add(Pair(exercise, ExerciseDetails(sets = defaultSets + 1, reps = defaultReps, weight = defaultWeight)))
                currentDuration += 1
                overlap -= 1
            } else {
                exercises.add(Pair(exercise, defaultDetails))
            }
        }
        if (currentDuration < duration.value) {
            Toast.makeText(LocalContext.current, "Only enough matching exercises for $currentDuration min", Toast.LENGTH_LONG).show()
        }
    }

    TabScreen(
        tabContent = {
            TrainingsPreviewScreenContent(
                musclePain = todaysMusclePainEntry,
                exercises = exercises,
                duration
            )
        },
        topBarTitle = Screen.TrainingsPreview.label,
        topBarIcon = Screen.TrainingsPreview.icon,
        navController = navController
    )
}

@Composable
fun TrainingsPreviewScreenContent(
    musclePain: MusclePainEntry?,
    exercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    duration: IntHolder
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        TextIconWidget(text = "Warm up", imageVector = Icons.Default.LocalFireDepartment)
        TextIconWidget(
            text = "Your ${duration.value} min Routine",
            imageVector = Icons.Default.FitnessCenter
        )
        exercises.forEach() { (exercise, details) ->
            ExerciseWidget(
                exercise = exercise,
                weight = details.weight,
                reps = details.reps,
                sets = details.sets,
                onRepsChanged = { details.reps = it },
                onSetsChanged = { details.sets = it },
            )
        }


        TextIconWidget(text = "Cool down", imageVector = Icons.Default.AcUnit)

    }
}