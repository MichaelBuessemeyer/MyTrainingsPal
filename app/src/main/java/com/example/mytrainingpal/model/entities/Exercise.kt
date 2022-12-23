package com.example.mytrainingpal.model.entities

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