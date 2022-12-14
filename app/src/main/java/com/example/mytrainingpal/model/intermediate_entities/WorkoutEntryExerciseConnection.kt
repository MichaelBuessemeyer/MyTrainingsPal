package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap

// Still being able to access the sets, reps, etc. we used the following post by MikeT as an approach accessed on 4th Dec. 2022.
// https://stackoverflow.com/questions/71383759/how-to-access-each-column-on-many-to-many-relationships-in-room-database-android
data class WorkoutEntryExerciseConnection(
    @Embedded
    val workoutEntryToExerciseConnection: WorkoutEntryExerciseMap,
    @Relation(
        entity = Exercise::class,
        parentColumn = "exerciseIdMap",
        entityColumn = "exerciseId"
    )
    val exercises: List<Exercise>
)
