package com.example.mytrainingpal.model

import androidx.room.*

// To create the modal package, we followed the tutorial posted by MikeT at
// https://stackoverflow.com/questions/68682797/could-someone-help-me-understand-room-persistence-library-better
// accessed on 4th Dec 2022.
@Entity()
data class Muscle(
    @PrimaryKey
    val muscleId: Long? = null,
    val name: String
)
