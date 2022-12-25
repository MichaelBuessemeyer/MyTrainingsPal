package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap


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
