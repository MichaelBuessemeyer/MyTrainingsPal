package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.Exercise


@Dao
abstract class ExerciseDao {
    @Insert
    abstract fun insert(exercise: Exercise): Long

    @Query("SELECT * FROM Exercise")
    abstract fun getAllExercises(): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercise WHERE exerciseId=:exerciseId")
    abstract fun getExerciseById(exerciseId: Long): Exercise

    @Update
    abstract fun updateExercise(exercise: Exercise)

    @Delete
    abstract fun deleteExercise(exercise: Exercise)
}