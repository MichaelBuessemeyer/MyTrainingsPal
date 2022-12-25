package com.example.mytrainingpal.model.view_models


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.repositories.WorkoutEntryExerciseMapRepository

class WorkoutEntryExerciseMapViewModel(application: Application) : ViewModel() {

    val allWorkoutEntryExerciseMaps: LiveData<List<WorkoutEntryExerciseMap>>
    private val repository: WorkoutEntryExerciseMapRepository
    val searchResults: MutableLiveData<List<WorkoutEntryExerciseMap>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(application)
        val workoutEntryExerciseMapDao = muscleDatabase.getWorkoutEntryExerciseMapDao()
        repository = WorkoutEntryExerciseMapRepository(workoutEntryExerciseMapDao)
        allWorkoutEntryExerciseMaps = repository.allWorkoutEntryExerciseMaps
        searchResults = repository.searchResults
    }

    fun insertWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap) {
        repository.insertWorkoutEntryExerciseMap(workoutEntryExerciseMap)
    }

    fun findWorkoutEntryExerciseMapById(id: Long) {
        repository.findWorkoutEntryExerciseMapeById(id)
    }

    fun deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap) {
        repository.deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap)
    }
}