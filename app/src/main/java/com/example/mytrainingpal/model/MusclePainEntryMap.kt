package com.example.mytrainingpal.model

import androidx.room.*


@Entity(
    primaryKeys = ["musclePainEntryIdMap", "muscleIdMap"],
    indices = [Index("musclePainEntryIdMap")],
    foreignKeys = [
        ForeignKey(
            entity = MusclePainEntry::class,
            parentColumns = ["musclePainEntryId"],
            childColumns = ["musclePainEntryIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class MusclePainEntryMap(
    val musclePainEntryIdMap: Long,
    val muscleIdMap: Long,
    val painIntensity: Long,
)