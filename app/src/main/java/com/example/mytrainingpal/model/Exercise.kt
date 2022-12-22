package com.example.mytrainingpal.model

import androidx.room.*
import java.util.Date


@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long? = null,
    val name: String,
    var pathToGif: String,
) {
}