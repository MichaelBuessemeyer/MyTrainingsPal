package com.example.mytrainingpal.model.entities

import androidx.room.*
import com.example.mytrainingpal.model.DateConverter
import java.util.Date


@Entity
data class MusclePainEntry(
    @PrimaryKey(autoGenerate = true)
    val musclePainEntryId: Long? = null,
    @TypeConverters(DateConverter::class)
    var date: Date
) {
}