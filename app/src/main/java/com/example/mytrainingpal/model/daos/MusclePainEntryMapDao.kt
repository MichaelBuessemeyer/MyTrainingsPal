package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles


@Dao
abstract class MusclePainEntryMapDao {
    @Insert
    abstract fun insert(musclePainEntryMap: MusclePainEntryMap): Long

    @Query("SELECT * FROM MusclePainEntryMap")
    abstract fun getAllSoreMuscleEntryMaps(): LiveData<List<MusclePainEntryMap>>

    @Query("SELECT * FROM MusclePainEntryMap WHERE musclePainEntryIdMap=:musclePainEntryIdMap")
    abstract fun getMusclePainEntryMapByMusclePainEntryIdMap(musclePainEntryIdMap: Long): MusclePainEntryMap

    @Transaction
    @Query("SELECT * FROM MusclePainEntry")
    abstract fun getAllMusclePainEntriesWithMuscles(): List<MusclePainEntryWithMuscles>

    @Transaction
    @Query("SELECT * FROM MusclePainEntry WHERE musclePainEntryId=:musclePainEntryId")
    abstract fun getAllMusclePainEntriesWithMusclesByMusclePainEntryId(musclePainEntryId: Long): List<MusclePainEntryWithMuscles>

    @Update
    abstract fun updateMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap)

    @Delete
    abstract fun deleteMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap)
}