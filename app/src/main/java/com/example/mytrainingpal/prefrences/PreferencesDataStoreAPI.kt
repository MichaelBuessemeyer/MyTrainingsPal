package com.example.mytrainingpal.prefrences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

// see https://proandroiddev.com/preference-datastore-the-generic-way-d26b11f1075f
interface PreferencesDataStoreAPI {
    suspend fun <T> getPreference(key: Preferences.Key<T>,defaultValue: T): Flow<T>
    // suspend fun <T> getAllPreferences(): Set<Flow<T>>
    // returns the preference in a static way without updating directly on change
    suspend fun <T> getFirstPreference(key: Preferences.Key<T>,defaultValue: T):T
    suspend fun <T> putPreference(key: Preferences.Key<T>, value:T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun <T> clearAllPreference()
}