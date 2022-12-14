package com.example.mytrainingpal.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    primaryKeys = ["workoutIdMap", "exerciseIdMap"],
    indices = [Index("workoutIdMap"), Index("exerciseIdMap")],
    foreignKeys = [
        ForeignKey(
            entity = WorkoutEntry::class,
            parentColumns = ["workoutId"],
            childColumns = ["workoutIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class WorkoutEntryExerciseMap(
    val workoutIdMap: Long,
    val exerciseIdMap: Long,
    val sets: Long,
    val reps: String,
    val weight: Long,
)