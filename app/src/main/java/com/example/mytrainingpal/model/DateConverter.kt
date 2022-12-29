package com.example.mytrainingpal.model

import androidx.room.TypeConverter
import java.util.*

// Taken from android docs accessed at 2nd Dec 2022
// https://developer.android.com/training/data-storage/room/referencing-data
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}