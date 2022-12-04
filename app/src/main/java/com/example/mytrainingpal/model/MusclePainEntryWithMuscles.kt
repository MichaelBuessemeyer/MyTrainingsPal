package com.example.mytrainingpal.model

import androidx.room.*


data class MusclePainEntryWithMuscles(
    @Embedded
    val musclePainEntry: MusclePainEntry,
    @Relation(
        entity = MusclePainEntryMap::class,
        parentColumn = "musclePainEntryId",
        entityColumn = "musclePainEntryIdMap",
    )
    val soreMusclesConnections: List<MusclePainEntryMusclesConnection>
)
