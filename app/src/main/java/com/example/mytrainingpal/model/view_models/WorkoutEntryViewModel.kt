package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.repositories.WorkoutEntryRepository

class WorkoutEntryViewModel(context: Context) : ViewModel() {

    val allWorkouts: LiveData<List<WorkoutEntry>>
    private val repository: WorkoutEntryRepository
    val searchResults: MutableLiveData<List<WorkoutEntry>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(context)
        val workoutEntryDao = muscleDatabase.getWorkoutEntryDao()
        repository = WorkoutEntryRepository(workoutEntryDao)
        allWorkouts = repository.allWorkouts
        searchResults = repository.searchResults
    }

    fun insertWorkoutEntry(workoutEntry: WorkoutEntry) {
        repository.insertWorkoutEntry(workoutEntry)
    }

    fun findWorkoutEntryById(id: Long) {
        repository.findWorkoutEntryById(id)
    }

    fun deleteWorkoutEntry(workoutEntry: WorkoutEntry) {
        repository.deleteWorkoutEntry(workoutEntry)
    }
}