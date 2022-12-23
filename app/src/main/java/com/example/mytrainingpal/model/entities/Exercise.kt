package com.example.mytrainingpal.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val exerciseId: Long? = null,
    val name: String,
    var pathToGif: String,
) {
}