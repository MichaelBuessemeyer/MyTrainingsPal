package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.repositories.MusclePainEntryMapRepository

class MusclePainEntryMapViewModel(context: Context) : ViewModel() {

    val allMusclePainEntryMaps: LiveData<List<MusclePainEntryMap>>
    private val repository: MusclePainEntryMapRepository
    val searchResults: MutableLiveData<List<MusclePainEntryMap>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(context)
        val musclePainEntryMapDao = muscleDatabase.getMusclePainEntryMapDao()
        repository = MusclePainEntryMapRepository(musclePainEntryMapDao)
        allMusclePainEntryMaps = repository.allMusclePainEntryMaps
        searchResults = repository.searchResults
    }

    fun insertMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap) {
        repository.insertMusclePainEntryMap(musclePainEntryMap)
    }

    fun findMusclePainEntryMapById(id: Long) {
        repository.findMusclePainEntryMapById(id)
    }

    fun deleteMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap) {
        repository.deleteMusclePainEntryMap(musclePainEntryMap)
    }
}