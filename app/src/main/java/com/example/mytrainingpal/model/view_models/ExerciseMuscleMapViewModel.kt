package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.repositories.ExerciseMuscleMapRepository

class ExerciseMuscleMapViewModel(context: Context) : ViewModel() {

    val allExerciseMuscleMaps: LiveData<List<ExerciseMuscleMap>>
    private val repository: ExerciseMuscleMapRepository
    val searchResults: MutableLiveData<List<ExerciseMuscleMap>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(context)
        val exerciseMuscleMapDao = muscleDatabase.getExerciseMuscleMapDao()
        repository = ExerciseMuscleMapRepository(exerciseMuscleMapDao)
        allExerciseMuscleMaps = repository.allExerciseMuscleMaps
        searchResults = repository.searchResults
    }

    fun insertExerciseMuscleMap(exerciseMuscleMap: ExerciseMuscleMap) {
        repository.insertExerciseMuscleMap(exerciseMuscleMap)
    }

    fun findExerciseMuscleMapById(id: Long) {
        repository.findExerciseMuscleMapById(id)
    }

    fun deleteExerciseMuscleMap(exerciseMuscleMap: ExerciseMuscleMap) {
        repository.deleteExerciseMuscleMap(exerciseMuscleMap)
    }
}