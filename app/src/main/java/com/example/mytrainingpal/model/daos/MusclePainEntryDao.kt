package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.MusclePainEntry

@Dao
abstract class MusclePainEntryDao {
    @Insert
    abstract fun insert(musclePainEntry: MusclePainEntry): Long

    @Query("SELECT * FROM MusclePainEntry")
    abstract fun getAllMusclePainEntries(): LiveData<List<MusclePainEntry>>

    // Method below probably not needed
    @Query("SELECT * FROM MusclePainEntry WHERE musclePainEntryId=:musclePainEntryId")
    abstract fun getMusclePainEntryById(musclePainEntryId: Long): MusclePainEntry

    @Update
    abstract fun updateMusclePainEntry(musclePainEntry: MusclePainEntry)

    @Delete
    abstract fun deleteMusclePainEntry(musclePainEntry: MusclePainEntry)
}