package com.example.mytrainingpal.model

import androidx.room.*


@Dao
abstract class MusclePainEntryMapDao {
    @Insert
    abstract fun insert(workoutExerciseMapDao: MusclePainEntryMap): Long
    @Query("SELECT * FROM MusclePainEntryMap")
    abstract fun getAllSoreMuscleEntryMaps(): List<MusclePainEntryMap>
    @Transaction
    @Query("SELECT * FROM MusclePainEntry")
    abstract fun getAllMusclePainEntriesWithMuscles(): List<MusclePainEntryWithMuscles>
}