package com.example.mytrainingpal.model.entities

import androidx.room.*
import com.example.mytrainingpal.model.DateConverter
import java.util.*


@Entity
data class WorkoutEntry(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Long? = null,
    @TypeConverters(DateConverter::class)
    val date: Date,
    @TypeConverters(DateConverter::class)
    val startTime: Date,
    @TypeConverters(DateConverter::class)
    val endTime: Date,
    val comment: String,
    val picturePath: String? = null
) {
}