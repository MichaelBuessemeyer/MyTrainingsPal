package com.example.mytrainingpal.model

import androidx.room.*
import java.time.LocalDate


@Entity
data class MusclePainEntry(
    @PrimaryKey(autoGenerate = true)
    val musclePainEntryId: Long? = null,
    val date: LocalDate
) {
}