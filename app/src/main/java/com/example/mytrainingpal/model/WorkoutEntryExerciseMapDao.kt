package com.example.mytrainingpal.model

import androidx.room.*


@Dao
abstract class WorkoutEntryExerciseMapDao {
    @Insert
    abstract fun insert(workoutEntryExerciseMap: WorkoutEntryExerciseMap): Long
    @Query("SELECT * FROM WorkoutEntryExerciseMap")
    abstract fun getAllWorkoutEntryExerciseMaps(): List<WorkoutEntryExerciseMap>
    @Transaction
    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntriesWithExercises(): List<WorkoutEntryWithExercises>
    @Transaction
    @Query("SELECT * FROM WorkoutEntryExerciseMap WHERE workoutIdMap=:workoutId")
    abstract fun getWorkoutEntryExerciseMapByWorkoutId(workoutId: Long): WorkoutEntryExerciseMap
    @Update
    abstract fun updateWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap)
    @Delete
    abstract fun deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap)

}