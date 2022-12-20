package com.example.mytrainingpal.model

import androidx.room.*
import java.util.Date


@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long? = null,
    @TypeConverters(DateConverter::class)
    val name: String,
    val pathToGif: String,
    val endTime: String,
    val comment: String,
    val picturePath: String? = null
) {
}