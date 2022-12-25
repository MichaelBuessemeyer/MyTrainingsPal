package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.ExerciseDao
import com.example.mytrainingpal.model.entities.Exercise
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    val allExercises: LiveData<List<Exercise>> = exerciseDao.getAllExercises()
    val searchResults = MutableLiveData<List<Exercise>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertExercise(newExercise: Exercise) {
        coroutineScope.launch(Dispatchers.IO) {
            exerciseDao.insert(newExercise)
        }
    }

    fun deleteExercise(exercise: Exercise) {
        coroutineScope.launch(Dispatchers.IO) {
            exerciseDao.deleteExercise(exercise)
        }
    }

    fun findExerciseById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<Exercise> =
        coroutineScope.async(Dispatchers.IO) {
            return@async exerciseDao.getExerciseById(id)
        }
}