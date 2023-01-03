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
    @Query("SELECT * FROM MusclePainEntry")
    abstract fun getAllMusclePainEntriesWithMusclesAsLiveData(): LiveData<List<MusclePainEntryWithMuscles>>

    @Transaction
    @Query("SELECT * FROM MusclePainEntry where musclePainEntryId=:musclePainEntryId")
    abstract fun getMusclePainEntryByIdWithMuscles(musclePainEntryId: Long): MusclePainEntryWithMuscles

    @Transaction
    @Query("INSERT INTO MusclePainEntryMap (musclePainEntryIdMap, painIntensity, muscleIdMap) select :musclePainEntryId, :painIntensity, muscleId FROM Muscle WHERE name = :muscleName")
    abstract fun insertForMusclePainEntryIdAndMuscleName(
        musclePainEntryId: Long,
        muscleName: String,
        painIntensity: Long
    ): Long

    @Transaction
    @Query("DELETE FROM MusclePainEntryMap WHERE musclePainEntryIdMap=:musclePainEntryId")
    abstract fun deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId: Long)

    @Transaction
    @Query("SELECT * FROM MusclePainEntry WHERE musclePainEntryId=:musclePainEntryId")
    abstract fun getAllMusclePainEntriesWithMusclesByMusclePainEntryId(musclePainEntryId: Long): List<MusclePainEntryWithMuscles>

    @Update
    abstract fun updateMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap)

    @Delete
    abstract fun deleteMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap)
}