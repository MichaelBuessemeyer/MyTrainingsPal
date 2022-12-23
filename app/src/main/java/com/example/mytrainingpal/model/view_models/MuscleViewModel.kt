package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.repositories.MuscleRepository

class MuscleViewModel(application: Application) : ViewModel() {

    val allMuscles: LiveData<List<Muscle>>
    private val repository: MuscleRepository
    val searchResults: MutableLiveData<List<Muscle>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val muscleDao = muscleDatabase.getMuscleDao()
        repository = MuscleRepository(muscleDao)
        allMuscles = repository.allMuscles
        searchResults = repository.searchResults
    }
}