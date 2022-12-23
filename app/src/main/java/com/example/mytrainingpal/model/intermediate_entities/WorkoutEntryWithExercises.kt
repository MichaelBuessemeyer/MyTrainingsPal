package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.*
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.intermediate_entities.WorkoutEntryExerciseConnection


data class WorkoutEntryWithExercises(
    @Embedded
    val workoutEntry: WorkoutEntry,
    @Relation(
        entity = WorkoutEntryExerciseMap::class,
        parentColumn = "workoutId",
        entityColumn = "workoutIdMap",
    )
    val exerciseConnections: List<WorkoutEntryExerciseConnection>
)
