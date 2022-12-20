package com.example.mytrainingpal.model

import androidx.room.*
import com.example.mytrainingpal.model.MusclePainEntry

@Dao
abstract class ExerciseMuscleMapDao {
    @Insert
    abstract fun insert(exerciseMuscleMap: ExerciseMuscleMap): Long
    @Query("SELECT * FROM ExerciseMuscleMap")
    abstract fun getAllMusclePainEntries(): List<ExerciseMuscleMap>
    // Method below probably not needed
    @Query("SELECT * FROM ExerciseMuscleMap WHERE exerciseIdMap=:exerciseId")
    abstract fun getExerciseMuscleMapByExerciseId(exerciseId: Long): ExerciseMuscleMap
}