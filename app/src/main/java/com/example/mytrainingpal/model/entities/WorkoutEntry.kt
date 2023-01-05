package com.example.mytrainingpal.model.entities

import androidx.room.*
import com.example.mytrainingpal.model.DateConverter
import java.util.*
import java.util.concurrent.TimeUnit


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
    val comment: String = "This is a standard comment.",
    val picturePath: String? = null,
) {
    val duration: Int
        get() = TimeUnit.MILLISECONDS.toMinutes(endTime.time - startTime.time).toInt()
}