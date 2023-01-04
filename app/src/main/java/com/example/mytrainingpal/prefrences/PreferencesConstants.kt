package com.example.mytrainingpal.prefrences

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

// see https://proandroiddev.com/preference-datastore-the-generic-way-d26b11f1075f
object PreferencesConstants {
    val AGE_KEY = intPreferencesKey("age")
    val USERNAME_KEY = stringPreferencesKey("user_name")
    val DEFAULT_BREAK_KEY = intPreferencesKey("default_break_time")
    val NOTIFICATION_TIME_KEY = stringPreferencesKey("notification_time")
    val NOTIFICATION_DAYS_KEY = stringSetPreferencesKey("notification_days")
    val PROFILE_PIC_KEY = stringPreferencesKey("profile_pic_link")

    val DAYS: List<String> = listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su")

    fun get(key: String): Preferences.Key<*> {
        return when (key) {
            "age" -> AGE_KEY
            "user_name" -> USERNAME_KEY
            "default_break_time" -> DEFAULT_BREAK_KEY
            "notification_time" -> NOTIFICATION_TIME_KEY
            "notification_days" -> NOTIFICATION_DAYS_KEY
            "profile_pic_link" -> PROFILE_PIC_KEY
            else -> throw IllegalArgumentException("key $key not found")
        }
    }
}