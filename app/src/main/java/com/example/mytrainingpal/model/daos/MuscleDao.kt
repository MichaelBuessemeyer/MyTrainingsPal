package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.Muscle

@Dao
abstract class MuscleDao {
    @Insert
    abstract fun insert(muscle: Muscle): Long
    @Query("SELECT * FROM Muscle")
    abstract fun getAllMuscles(): LiveData<List<Muscle>>
    @Query("SELECT * FROM Muscle WHERE muscleId=:muscleId")
    abstract fun getMuscleById(muscleId: Long): Muscle
    @Update
    abstract fun updateMuscle(exercise: Muscle)
    @Delete
    abstract fun deleteMuscle(exercise: Muscle)
}