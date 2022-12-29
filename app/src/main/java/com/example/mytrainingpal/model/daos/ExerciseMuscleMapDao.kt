package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.intermediate_entities.ExerciseWithMuscles

@Dao
abstract class ExerciseMuscleMapDao {
    @Insert
    abstract fun insert(exerciseMuscleMap: ExerciseMuscleMap): Long

    @Query("SELECT * FROM ExerciseMuscleMap")
    abstract fun getAllExerciseMuscleMaps(): LiveData<List<ExerciseMuscleMap>>

    @Query("SELECT * FROM Exercise")
    abstract fun getAllExercisesWithMuscles(): LiveData<List<ExerciseWithMuscles>>

    // Method below probably not needed
    @Query("SELECT * FROM ExerciseMuscleMap WHERE exerciseIdMap=:exerciseId")
    abstract fun getExerciseMuscleMapByExerciseId(exerciseId: Long): ExerciseMuscleMap

    @Update
    abstract fun updateExerciseMuscleMap(exercise: ExerciseMuscleMap)

    @Delete
    abstract fun deleteExerciseMuscleMap(exercise: ExerciseMuscleMap)
}