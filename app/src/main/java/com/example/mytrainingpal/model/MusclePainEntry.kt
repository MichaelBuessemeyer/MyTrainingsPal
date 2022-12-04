package com.example.mytrainingpal.model

import androidx.room.*
import java.util.Date


@Entity
data class MusclePainEntry(
    @PrimaryKey(autoGenerate = true)
    val musclePainEntryId: Long? = null,
    @TypeConverters(DateConverter::class)
    val date: Date
) {
}