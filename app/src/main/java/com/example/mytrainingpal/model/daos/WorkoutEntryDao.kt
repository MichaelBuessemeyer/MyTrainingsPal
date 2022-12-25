package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.WorkoutEntry

@Dao
abstract class WorkoutEntryDao {
    @Insert
    abstract fun insert(workoutEntry: WorkoutEntry): Long

    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntries(): LiveData<List<WorkoutEntry>>

    @Query("SELECT * FROM WorkoutEntry WHERE workoutId=:workoutId")
    abstract fun getWorkoutEntryById(workoutId: Long): WorkoutEntry

    @Update
    abstract fun updateWorkoutEntry(workoutEntry: WorkoutEntry)

    @Delete
    abstract fun deleteWorkoutEntry(workoutEntry: WorkoutEntry)
}