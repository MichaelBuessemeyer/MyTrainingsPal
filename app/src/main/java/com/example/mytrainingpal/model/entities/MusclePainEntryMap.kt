package com.example.mytrainingpal.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry


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
    const val MODERATE_PAIN: Long = 1
}