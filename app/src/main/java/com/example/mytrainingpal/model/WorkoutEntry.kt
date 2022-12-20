package com.example.mytrainingpal.model

import androidx.room.*
import java.util.Date


@Entity
data class WorkoutEntry(
    @PrimaryKey(autoGenerate = true)
    val workoutId: Long? = null,
    @TypeConverters(DateConverter::class)
    val date: Date,
    val startTime: String,
    val endTime: String,
    val comment: String,
    val picturePath: String? = null
) {
}