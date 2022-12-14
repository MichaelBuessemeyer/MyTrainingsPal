package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.entities.Muscle

// Still being able to access the soreValue we used the following post by MikeT as an approach accessed on 4th Dec. 2022.
// https://stackoverflow.com/questions/71383759/how-to-access-each-column-on-many-to-many-relationships-in-room-database-android
data class ExerciseMusclesConnection(
    @Embedded
    val exerciseToMuscleConnection: ExerciseMuscleMap,
    @Relation(
        entity = Muscle::class,
        parentColumn = "muscleIdMap",
        entityColumn = "muscleId"
    )
    val muscles: List<Muscle>
)
