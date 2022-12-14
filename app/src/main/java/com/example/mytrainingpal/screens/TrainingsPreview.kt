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
import com.example.mytrainingpal.composables.*
import com.example.mytrainingpal.model.MusclePainEntryMapConstants
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.view_models.ExerciseMuscleMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.model.view_models.PreferencesViewModel
import com.example.mytrainingpal.preferences.PreferencesConstants
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState
import com.example.mytrainingpal.util.ExerciseDetails
import com.example.mytrainingpal.util.IntHolder
import com.example.mytrainingpal.util.TimeHolder
import java.util.*


@Composable
fun TrainingsPreviewScreen(
    navController: NavController,
    duration: IntHolder,
    exercises: MutableList<Pair<Exercise, ExerciseDetails>>,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
    exerciseMuscleMapViewModel: ExerciseMuscleMapViewModel,
    preferencesViewModel: PreferencesViewModel,
    startTime: TimeHolder
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
    val preferencesState by preferencesViewModel.allPreferences.collectAsState(mapOf<String, Any>())
    val breakTime: Int =
        (preferencesState[PreferencesConstants.DEFAULT_BREAK_KEY.name] ?: 30) as Int

    remember(todaysMusclePainEntry, allExercises) {
        val slightlySoreMuscles: Set<Muscle> =
            soreMuscles.filter { it.second == MusclePainEntryMapConstants.MODERATE_PAIN }
                .map { it.first }.toSet()
        val verySoreMuscles: Set<Muscle> =
            soreMuscles.filter { it.second == MusclePainEntryMapConstants.SEVERE_PAIN }
                .map { it.first }.toSet()

        val unsoreExercises: List<Exercise> = allExercises.filter { exerciseWithMuscles ->
            val exerciseMuscles = exerciseWithMuscles.muscleConnections.map { it.muscles[0] }
            exerciseMuscles.intersect(slightlySoreMuscles).isEmpty()
                    && exerciseMuscles.intersect(verySoreMuscles).isEmpty()
        }.map { (exercise) -> exercise }
        val slightlySoreExercises: List<Exercise> =
            allExercises.filter { exerciseWithMuscles ->
                val exerciseMuscles = exerciseWithMuscles.muscleConnections.map { it.muscles[0] }
                exerciseMuscles.intersect(slightlySoreMuscles).isNotEmpty()
                        && exerciseMuscles.intersect(verySoreMuscles).isEmpty()
            }.map { (exercise) -> exercise }

        // we assume one set takes 1 minute
        val setDuration = 60 // TODO: do this properly and not with one default for all sets
        val defaultReps = 10
        val defaultSets = 3
        val defaultWeight = 20
        val defaultDetails = ExerciseDetails(
            sets = defaultSets,
            reps = List<String>(defaultSets) { "$defaultReps" }.joinToString(","),
            weight = defaultWeight
        )

        val durationSeconds = duration.value * 60
        val setsNeeded = (durationSeconds + breakTime) / (setDuration + breakTime)
        var setsLeftover = setsNeeded
        val selectedExercises: MutableList<Pair<Exercise, ExerciseDetails>> = mutableListOf()
        if (unsoreExercises.isNotEmpty() || slightlySoreExercises.isNotEmpty()) {
            if (unsoreExercises.size < (setsNeeded / defaultSets)) {
                // fill the workout first with unsore exercises
                selectedExercises.addAll(unsoreExercises.map { Pair(it, defaultDetails) })
                setsLeftover -= (selectedExercises.size * defaultSets)
                // then fill the workout with slightly sore exercises of full default sets
                if (setsLeftover > defaultSets) {
                    val slightlySoreExercisesNeeded = setsLeftover / defaultSets
                    selectedExercises.addAll(
                        slightlySoreExercises.asSequence().shuffled()
                            .take(slightlySoreExercisesNeeded)
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
                        reps = selectedExercises[i].second.reps + ",$defaultReps",
                        weight = selectedExercises[i].second.weight
                    )
                )
                setsLeftover--
                i++
                if (i >= selectedExercises.size) {
                    i = 0
                }
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
                startTime.value = Date()
                navController.navigate(Screen.Toggle.route)
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
        // no lazyColumn here, because we know there won't be that many entries ;)
        exercises.forEach { (exercise, details) ->
            var reps by remember { mutableStateOf(details.reps.split(",")[0].toInt()) }
            var sets by remember { mutableStateOf(details.sets) }
            ExerciseWidget(
                exercise = exercise,
                weight = details.weight,
                reps = reps,
                sets = sets,
                onRepsChanged = {
                    reps = it
                    details.reps = List<String>(sets) { "$reps" }.joinToString(",")
                },
                onSetsChanged = {
                    sets = it
                    details.sets = it
                    details.reps = List<String>(sets) { "$reps" }.joinToString(",")
                },
            )
        }
        //TextIconWidget(text = "Cool down", imageVector = Icons.Default.AcUnit)
    }
}