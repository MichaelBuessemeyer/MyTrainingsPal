package com.example.mytrainingpal.model

import androidx.room.*


@Entity(
    primaryKeys = ["exerciseIdMap", "muscleIdMap"],
    indices = [Index("exerciseIdMap"), Index("muscleIdMap")],
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Muscle::class,
            parentColumns = ["muscleId"],
            childColumns = ["muscleIdMap"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseMuscleMap(
    val exerciseIdMap: Long,
    var muscleIdMap: Long,
)