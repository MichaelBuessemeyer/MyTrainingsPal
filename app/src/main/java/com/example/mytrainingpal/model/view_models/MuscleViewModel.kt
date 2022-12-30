package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.repositories.MuscleRepository

class MuscleViewModel(context: Context) : ViewModel() {

    val allMuscles: LiveData<List<Muscle>>
    private val repository: MuscleRepository
    val searchResults: MutableLiveData<List<Muscle>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(context)
        val muscleDao = muscleDatabase.getMuscleDao()
        repository = MuscleRepository(muscleDao)
        allMuscles = repository.allMuscles
        searchResults = repository.searchResults
    }

    fun insertMuscle(muscle: Muscle) {
        repository.insertMuscle(muscle)
    }

    fun findMuscleById(id: Long) {
        repository.findMuscleById(id)
    }

    fun deleteMuscle(muscle: Muscle) {
        repository.deleteMuscle(muscle)
    }
}