package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.repositories.ExerciseRepository

class ExerciseViewModel(application: Application) : ViewModel() {

    val allExercises: LiveData<List<Exercise>>
    private val repository: ExerciseRepository
    val searchResults: MutableLiveData<List<Exercise>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val exerciseDao = muscleDatabase.getExerciseDao()
        repository = ExerciseRepository(exerciseDao)
        allExercises = repository.allExercises
        searchResults = repository.searchResults
    }

    fun insertExercise(exercise: Exercise) {
        repository.insertExercise(exercise)
    }

    fun findExerciseById(id: Long) {
        repository.findExerciseById(id)
    }

    fun deleteExercise(exercise: Exercise) {
        repository.deleteExercise(exercise)
    }
}