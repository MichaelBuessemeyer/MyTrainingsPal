package com.example.mytrainingpal.prefrences


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytrainingpal.prefrences.PreferencesConstants.USERNAME_KEY
import kotlinx.coroutines.launch

// usage of DataStore learned from https://proandroiddev.com/preference-datastore-the-generic-way-d26b11f1075f
// in combination with https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data
class PreferencesViewModel(context: Context) : ViewModel() {
    private val preferencesDataStoreHelper: PreferencesDataStoreHelper
    var userName by mutableStateOf<String>("def mut state VM")

    init {
        preferencesDataStoreHelper = PreferencesDataStoreHelper(context)
        viewModelScope.launch {
            userName = preferencesDataStoreHelper.getFirstPreference(
                USERNAME_KEY,
                "init view model"
            )
        }
    }

    fun setName(newName: String) {
        viewModelScope.launch { preferencesDataStoreHelper.putPreference(USERNAME_KEY, newName) }
    }

    suspend fun getName() {
        preferencesDataStoreHelper.getPreference(USERNAME_KEY, "def getName")
    }

}