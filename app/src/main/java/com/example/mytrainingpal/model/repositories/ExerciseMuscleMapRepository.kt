package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.ExerciseMuscleMapDao
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.intermediate_entities.ExerciseWithMuscles
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class ExerciseMuscleMapRepository(private val exerciseMuscleMapDao: ExerciseMuscleMapDao) {

    val allExerciseMuscleMaps: LiveData<List<ExerciseMuscleMap>> =
        exerciseMuscleMapDao.getAllExerciseMuscleMaps()
    val allExercisesWithMuscles: LiveData<List<ExerciseWithMuscles>> =
        exerciseMuscleMapDao.getAllExercisesWithMuscles()
    val searchResults = MutableLiveData<List<ExerciseMuscleMap>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertExerciseMuscleMap(newExerciseMuscleMap: ExerciseMuscleMap) {
        coroutineScope.launch(Dispatchers.IO) {
            exerciseMuscleMapDao.insert(newExerciseMuscleMap)
        }
    }

    fun deleteExerciseMuscleMap(exerciseMuscleMap: ExerciseMuscleMap) {
        coroutineScope.launch(Dispatchers.IO) {
            exerciseMuscleMapDao.deleteExerciseMuscleMap(exerciseMuscleMap)
        }
    }

    fun findExerciseMuscleMapById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<ExerciseMuscleMap> =
        coroutineScope.async(Dispatchers.IO) {
            return@async exerciseMuscleMapDao.getExerciseMuscleMapByExerciseId(id)
        }
}