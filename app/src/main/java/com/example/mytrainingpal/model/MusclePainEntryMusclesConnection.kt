package com.example.mytrainingpal.model

import androidx.room.*

// Still being able to access the soreValue we used the following post by MikeT as an approach accessed on 4th Dec. 2022.
// https://stackoverflow.com/questions/71383759/how-to-access-each-column-on-many-to-many-relationships-in-room-database-android
data class MusclePainEntryMusclesConnection(
    @Embedded
    val musclePainEntryToMuscleConnection: MusclePainEntryMap,
    @Relation(
        entity = Muscle::class,
        parentColumn = "muscleIdMap",
        entityColumn = "muscleId"
    )
    val soreMuscles: List<Muscle>
)
