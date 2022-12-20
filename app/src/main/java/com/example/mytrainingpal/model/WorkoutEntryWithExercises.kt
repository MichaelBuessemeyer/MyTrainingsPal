package com.example.mytrainingpal.model

import androidx.room.*


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
