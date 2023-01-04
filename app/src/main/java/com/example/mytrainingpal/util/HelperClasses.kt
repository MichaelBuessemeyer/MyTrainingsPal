package com.example.mytrainingpal.util

import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import java.util.*

class ExerciseDetails(var sets: Int, var reps: String, var weight: Int)

data class TrainingStats(
    val durationInMinutes: Int,
    val totalWeightLifted: Int,
    val date: Date?
)

data class CalendarEntry(
    val date: Date,
    val musclePainEntry: MusclePainEntryWithMuscles?=null,
    val workout: WorkoutEntry?=null,
)

// used to pass ints by reference
class IntHolder(var value: Int)

class TimeHolder(var value: Date)