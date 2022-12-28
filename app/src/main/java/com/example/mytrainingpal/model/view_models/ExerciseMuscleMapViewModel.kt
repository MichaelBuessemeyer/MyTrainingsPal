package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.intermediate_entities.ExerciseWithMuscles
import com.example.mytrainingpal.model.repositories.ExerciseMuscleMapRepository

class ExerciseMuscleMapViewModel(application: Application) : ViewModel() {

    val allExerciseMuscleMaps: LiveData<List<ExerciseMuscleMap>>
    val allExercisesWithMuscles: LiveData<List<ExerciseWithMuscles>>
    private val repository: ExerciseMuscleMapRepository
    val searchResults: MutableLiveData<List<ExerciseMuscleMap>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val exerciseMuscleMapDao = muscleDatabase.getExerciseMuscleMapDao()
        repository = ExerciseMuscleMapRepository(exerciseMuscleMapDao)
        allExerciseMuscleMaps = repository.allExerciseMuscleMaps
        allExercisesWithMuscles = repository.allExercisesWithMuscles
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