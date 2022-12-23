package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.repositories.MusclePainEntryRepository

class MusclePainEntryViewModel(application: Application) : ViewModel() {

    val allMusclePainEntries: LiveData<List<MusclePainEntry>>
    private val repository: MusclePainEntryRepository
    val searchResults: MutableLiveData<List<MusclePainEntry>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val musclePainEntryDao = muscleDatabase.getMusclePainEntryDao()
        repository = MusclePainEntryRepository(musclePainEntryDao)
        allMusclePainEntries = repository.allMusclePainEntries
        searchResults = repository.searchResults
    }
}