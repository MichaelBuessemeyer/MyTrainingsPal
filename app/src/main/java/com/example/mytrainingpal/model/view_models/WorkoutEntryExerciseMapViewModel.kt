package com.example.mytrainingpal.model.view_models


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.intermediate_entities.WorkoutEntryWithExercises
import com.example.mytrainingpal.model.repositories.WorkoutEntryExerciseMapRepository

class WorkoutEntryExerciseMapViewModel(context: Context) : ViewModel() {

    val allWorkoutEntryExerciseMaps: LiveData<List<WorkoutEntryExerciseMap>>
    val allWorkoutEntriesWithExercises: LiveData<List<WorkoutEntryWithExercises>>
    private val repository: WorkoutEntryExerciseMapRepository
    val searchResults: MutableLiveData<List<WorkoutEntryExerciseMap>>

    init {
        val muscleDatabase = TheMuscleBase.getDatabaseInstance(context)
        val workoutEntryExerciseMapDao = muscleDatabase.getWorkoutEntryExerciseMapDao()
        repository = WorkoutEntryExerciseMapRepository(workoutEntryExerciseMapDao)
        allWorkoutEntryExerciseMaps = repository.allWorkoutEntryExerciseMaps
        allWorkoutEntriesWithExercises = repository.allWorkoutEntriesWithExercises
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