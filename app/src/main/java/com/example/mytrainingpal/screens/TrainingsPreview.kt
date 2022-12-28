package com.example.mytrainingpal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.view_models.ExerciseMuscleMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
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
    exerciseMuscleMapViewModel: ExerciseMuscleMapViewModel,
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

    remember(todaysMusclePainEntry, allExercises) {
        val slightlySoreMuscles: Set<Muscle> =
            soreMuscles.filter { it.second == 1L }.map { it.first }.toSet()
        val verySoreMuscles: Set<Muscle> =
            soreMuscles.filter { it.second == 2L }.map { it.first }.toSet()

        val unsoreExercises: MutableList<Exercise> = allExercises.filter { exerciseWithMuscles ->
            val exerciseMuscles = exerciseWithMuscles.muscleConnections.map { it.muscles[0] }
            exerciseMuscles.intersect(slightlySoreMuscles).isEmpty()
                    && exerciseMuscles.intersect(verySoreMuscles).isEmpty()
        }.map { (exercise) -> exercise }.toMutableList()
        val slightlySoreExercises: MutableList<Exercise> =
            allExercises.filter { exerciseWithMuscles ->
                val exerciseMuscles = exerciseWithMuscles.muscleConnections.map { it.muscles[0] }
                exerciseMuscles.intersect(slightlySoreMuscles).isNotEmpty()
                        && exerciseMuscles.intersect(verySoreMuscles).isEmpty()
            }.map { (exercise) -> exercise }.toMutableList()

        val defaultReps = 10
        val defaultSets = 3
        val defaultWeight = 20
        val defaultDetails = ExerciseDetails(
            sets = defaultSets,
            reps = defaultReps,
            weight = defaultWeight
        )

        val setsNeeded = (duration.value / 2) + 1
        var setsLeftover = setsNeeded
        val selectedExercises: MutableList<Pair<Exercise, ExerciseDetails>> = mutableListOf()

        if (unsoreExercises.size < (setsNeeded / defaultSets)) {
            // fill the workout first with unsore exercises
            selectedExercises.addAll(unsoreExercises.map { Pair(it, defaultDetails) })
            setsLeftover -= (selectedExercises.size * defaultSets)
            // then fill the workout with slightly sore exercises of full default sets
            if (setsLeftover > defaultSets) {
                val slightlySoreExercisesNeeded = setsLeftover / defaultSets
                selectedExercises.addAll(
                    slightlySoreExercises.asSequence().shuffled().take(slightlySoreExercisesNeeded)
                        .map { Pair(it, defaultDetails) })
                setsLeftover -= defaultSets * slightlySoreExercisesNeeded
            }
        } else {
            selectedExercises.addAll(
                unsoreExercises.asSequence().shuffled().take(setsNeeded / defaultSets)
                    .map { Pair(it, defaultDetails) })
            setsLeftover -= (selectedExercises.size * defaultSets)
        }
        // then increase the amount of sets one after another till the time is up
        var i = 0
        while (setsLeftover > 0) {
            selectedExercises[i] = Pair(
                selectedExercises[i].first, ExerciseDetails(
                    sets = selectedExercises[i].second.sets + 1,
                    reps = selectedExercises[i].second.reps,
                    weight = selectedExercises[i].second.weight
                )
            )
            setsLeftover--
            i++
            if (i >= selectedExercises.size) {
                i = 0
            }
        }
        exercises.clear()
        exercises.addAll(selectedExercises)

    }

    TabScreen(
        tabContent = {
            TrainingsPreviewScreenContent(
                exercises = exercises,
                duration
            )
        },
        topBarTitle = Screen.TrainingsPreview.label,
        topBarIcon = Screen.TrainingsPreview.icon,
        navController = navController,
        floatingActionButton = {
            StartExerciseFloatingButton {
                navController.navigate(Screen.Home.route)
            }
        }
    )
}

@Composable
fun TrainingsPreviewScreenContent(
    exercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    duration: IntHolder
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        //TextIconWidget(text = "Warm up", imageVector = Icons.Default.LocalFireDepartment)
        TextIconWidget(
            text = "Your ${duration.value} min Routine",
            imageVector = Icons.Default.FitnessCenter
        )
        exercises.forEach() { (exercise, details) ->
            var reps by remember { mutableStateOf(details.reps) }
            var sets by remember { mutableStateOf(details.sets) }
            ExerciseWidget(
                exercise = exercise,
                weight = details.weight,
                reps = reps,
                sets = sets,
                onRepsChanged = {
                    reps = it
                    details.reps = it
                },
                onSetsChanged = {
                    sets = it
                    details.sets = it
                },
            )
        }
        //TextIconWidget(text = "Cool down", imageVector = Icons.Default.AcUnit)
    }
}