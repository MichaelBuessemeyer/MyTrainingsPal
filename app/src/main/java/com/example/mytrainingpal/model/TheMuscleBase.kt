package com.example.mytrainingpal.model

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase


@Database(entities = [MusclePainEntry::class,Muscle::class,MusclePainEntryMap::class],version = 1)
@TypeConverters(DateConverter::class)
abstract class TheMuscleBase: RoomDatabase() {

    abstract fun getMuscleDao(): MuscleDao
    abstract fun getMusclePainEntryDao(): MusclePainEntryDao
    abstract fun getMusclePainEntryMapDao(): MusclePainEntryMapDao

    companion object {

        @Volatile
        private var instance: TheMuscleBase? = null

        fun getDatabaseInstance(context: Context): TheMuscleBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context,TheMuscleBase::class.java,"musclebase.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as TheMuscleBase
        }
    }
}