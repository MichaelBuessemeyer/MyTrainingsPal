package com.example.mytrainingpal.prefrences

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
}