package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytrainingpal.preferences.PreferencesConstants.AGE_KEY
import com.example.mytrainingpal.preferences.PreferencesConstants.DAYS
import com.example.mytrainingpal.preferences.PreferencesConstants.DEFAULT_BREAK_KEY
import com.example.mytrainingpal.preferences.PreferencesConstants.NOTIFICATION_DAYS_KEY
import com.example.mytrainingpal.preferences.PreferencesConstants.NOTIFICATION_TIME_KEY
import com.example.mytrainingpal.preferences.PreferencesConstants.USERNAME_KEY
import com.example.mytrainingpal.preferences.PreferencesDataStoreHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

// usage of DataStore learned from https://proandroiddev.com/preference-datastore-the-generic-way-d26b11f1075f
// in combination with https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data
class PreferencesViewModel(context: Context) : ViewModel() {
    private val preferencesDataStoreHelper: PreferencesDataStoreHelper
    var allPreferences: Flow<Map<String, Any>> = flowOf(
        mapOf(
            USERNAME_KEY.name to "default",
            AGE_KEY.name to 20,
            DEFAULT_BREAK_KEY.name to 30,
            NOTIFICATION_DAYS_KEY.name to setOf(DAYS[0], DAYS[2], DAYS[4])
        )
    )

    init {
        preferencesDataStoreHelper = PreferencesDataStoreHelper(context)
        viewModelScope.launch {
            allPreferences = preferencesDataStoreHelper.getPreferences()
        }
    }

    fun deleteAllData() {
        viewModelScope.launch {
            preferencesDataStoreHelper.clearAllPreference()
        }
    }

    fun setName(newName: String) {
        viewModelScope.launch { preferencesDataStoreHelper.putPreference(USERNAME_KEY, newName) }
    }

    fun setDefaultBreak(newDefaultBreak: Int) {
        viewModelScope.launch {
            preferencesDataStoreHelper.putPreference(
                DEFAULT_BREAK_KEY,
                newDefaultBreak
            )
        }
    }

    fun setAge(newAge: Int) {
        viewModelScope.launch {
            preferencesDataStoreHelper.putPreference(
                AGE_KEY,
                newAge
            )
        }
    }

    fun setNotificationTime(newNotificationTime: String) {
        viewModelScope.launch {
            preferencesDataStoreHelper.putPreference(
                NOTIFICATION_TIME_KEY,
                newNotificationTime
            )
        }
    }

    fun setNotificationDays(newNotificationDays: Set<String>) {
        println(newNotificationDays.toString())
        viewModelScope.launch {
            preferencesDataStoreHelper.putPreference(
                NOTIFICATION_DAYS_KEY,
                newNotificationDays
            )
        }
    }

}