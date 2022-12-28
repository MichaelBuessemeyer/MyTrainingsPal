package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import com.example.mytrainingpal.model.repositories.MusclePainEntryMapRepository

class MusclePainEntryMapViewModel(application: Application) : ViewModel() {

    val allMusclePainEntryMaps: LiveData<List<MusclePainEntryMap>>
    val allMusclePainEntriesWithMuscles: LiveData<List<MusclePainEntryWithMuscles>>
    private val repository: MusclePainEntryMapRepository
    val searchResults: MutableLiveData<List<MusclePainEntryMap>>
    val searchResult: MutableLiveData<MusclePainEntryWithMuscles>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val musclePainEntryMapDao = muscleDatabase.getMusclePainEntryMapDao()
        repository = MusclePainEntryMapRepository(musclePainEntryMapDao)
        allMusclePainEntryMaps = repository.allMusclePainEntryMaps
        allMusclePainEntriesWithMuscles = repository.allMusclePainEntriesWithMuscles
        searchResults = repository.searchResults
        searchResult = repository.searchResult
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

    fun deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId: Long) {
        repository.deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId)
    }

    fun deleteAllSoreMusclesForMusclePainEntryOnCurrentThread(musclePainEntryId: Long) {
        repository.deleteAllSoreMusclesForMusclePainEntryOnCurrentThread(musclePainEntryId)
    }

    fun getMusclePainEntryByIdWithMuscles(id: Long) {
        repository.getMusclePainEntryByIdWithMuscles(id)
    }

    fun insertForMusclePainEntryIdAndMuscleName(
        musclePainEntryId: Long,
        muscleName: String,
        painIntensity: Long
    ) {
        repository.insertForMusclePainEntryIdAndMuscleName(
            musclePainEntryId,
            muscleName,
            painIntensity
        )
    }
}