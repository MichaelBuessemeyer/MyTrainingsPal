package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.*
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.entities.MusclePainEntry


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
