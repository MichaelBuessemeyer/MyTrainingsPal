package com.example.mytrainingpal.model

import androidx.room.*

@Dao
abstract class WorkoutEntryDao {
    @Insert
    abstract fun insert(exercise: WorkoutEntry): Long
    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntries(): List<WorkoutEntry>
    @Query("SELECT * FROM WorkoutEntry WHERE workoutId=:workoutId")
    abstract fun getWorkoutEntryById(workoutId: Long): WorkoutEntry
}