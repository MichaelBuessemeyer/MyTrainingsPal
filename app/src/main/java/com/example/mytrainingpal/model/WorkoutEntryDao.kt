package com.example.mytrainingpal.model

import androidx.room.*

@Dao
abstract class WorkoutEntryDao {
    @Insert
    abstract fun insert(workoutEntry: WorkoutEntry): Long
    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntries(): List<WorkoutEntry>
    @Query("SELECT * FROM WorkoutEntry WHERE workoutId=:workoutId")
    abstract fun getWorkoutEntryById(workoutId: Long): WorkoutEntry
    @Update
    abstract fun updateWorkoutEntry(workoutEntry: WorkoutEntry)
    @Delete
    abstract fun deleteWorkoutEntry(workoutEntry: WorkoutEntry)
}