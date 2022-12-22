package com.example.mytrainingpal.model

import androidx.room.*



@Entity(
    primaryKeys = ["musclePainEntryIdMap", "muscleIdMap"],
    indices = [Index("musclePainEntryIdMap"), Index("muscleIdMap")],
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
    var painIntensity: Long,
)

object MusclePainEntryMapConstants {
    const val SEVERE_PAIN: Long = 2
    const val MODERATE_PAIN: Long = 2
}