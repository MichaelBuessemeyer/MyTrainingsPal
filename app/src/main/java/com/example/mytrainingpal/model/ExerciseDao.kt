package com.example.mytrainingpal.model

import androidx.room.*


@Dao
abstract class ExerciseDao {
    @Insert
    abstract fun insert(exercise: Exercise): Long
    @Query("SELECT * FROM Exercise")
    abstract fun getAllExercises(): List<Exercise>
    @Query("SELECT * FROM Exercise WHERE exerciseId=:exerciseId")
    abstract fun getExerciseById(exerciseId: Long): Exercise
    @Update
    abstract fun updateExercise(exercise: Exercise)
    @Delete
    abstract fun deleteExercise(exercise: Exercise)
}