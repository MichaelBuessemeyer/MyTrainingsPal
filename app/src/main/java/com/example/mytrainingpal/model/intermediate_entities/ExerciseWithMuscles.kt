package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap

// TODO: FixMe - do this without the ExerciseMuscleConnection indirection
// and just with a simple muscles: List<Muscle> field.
data class ExerciseWithMuscles(
    @Embedded val exercise: Exercise, @Relation(
        entity = ExerciseMuscleMap::class,
        parentColumn = "exerciseId",
        entityColumn = "exerciseIdMap",
    )

    val muscleConnections: List<ExerciseMusclesConnection>
)
