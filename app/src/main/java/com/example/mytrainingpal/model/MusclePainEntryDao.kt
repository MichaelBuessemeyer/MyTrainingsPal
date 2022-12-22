package com.example.mytrainingpal.model

import androidx.room.*
import com.example.mytrainingpal.model.MusclePainEntry

@Dao
abstract class MusclePainEntryDao {
    @Insert
    abstract fun insert(musclePainEntry: MusclePainEntry): Long
    @Query("SELECT * FROM MusclePainEntry")
    abstract fun getAllMusclePainEntries(): List<MusclePainEntry>
    // Method below probably not needed
    @Query("SELECT * FROM MusclePainEntry WHERE musclePainEntryId=:musclePainEntryId")
    abstract fun getMusclePainEntryById(musclePainEntryId: Long): MusclePainEntry
    @Update
    abstract fun updateMusclePainEntry(musclePainEntry: MusclePainEntry)
    @Delete
    abstract fun deleteMusclePainEntry(musclePainEntry: MusclePainEntry)
}