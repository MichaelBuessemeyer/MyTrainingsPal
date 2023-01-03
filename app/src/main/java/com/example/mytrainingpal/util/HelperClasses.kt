package com.example.mytrainingpal.util

import java.util.*

class ExerciseDetails(var sets: Int, var reps: String, var weight: Int)

data class TrainingStats(
    val durationInMinutes: Int,
    val totalWeightLifted: Int,
    val date: Date?
)

// used to pass ints by reference
class IntHolder(var value: Int)