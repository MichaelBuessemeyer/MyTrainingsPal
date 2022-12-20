package com.example.mytrainingpal.model

import androidx.room.*


@Dao
abstract class WorkoutEntryExerciseMapDao {
    @Insert
    abstract fun insert(workoutExerciseMapDao: WorkoutEntryExerciseMap): Long
    @Query("SELECT * FROM WorkoutEntryExerciseMap")
    abstract fun getAllWorkoutEntryExerciseMaps(): List<WorkoutEntryExerciseMap>
    @Transaction
    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntriesWithExercises(): List<MusclePainEntryWithMuscles>
}