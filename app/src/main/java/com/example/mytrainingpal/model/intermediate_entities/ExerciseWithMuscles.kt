package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap


data class ExerciseWithMuscles(
    @Embedded
    val exercise: Exercise,
    @Relation(
        entity = ExerciseMuscleMap::class,
        parentColumn = "exerciseId",
        entityColumn = "exerciseIdMap",
    )

    val muscleConnections: List<ExerciseMusclesConnection>
)
