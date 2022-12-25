package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.MuscleDao
import com.example.mytrainingpal.model.entities.Muscle
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class MuscleRepository(private val muscleDao: MuscleDao) {

    val allMuscles: LiveData<List<Muscle>> = muscleDao.getAllMuscles()
    val searchResults = MutableLiveData<List<Muscle>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertMuscle(newMuscle: Muscle) {
        coroutineScope.launch(Dispatchers.IO) {
            muscleDao.insert(newMuscle)
        }
    }

    fun deleteMuscle(muscle: Muscle) {
        coroutineScope.launch(Dispatchers.IO) {
            muscleDao.deleteMuscle(muscle)
        }
    }

    fun findMuscleById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<Muscle> =
        coroutineScope.async(Dispatchers.IO) {
            return@async muscleDao.getMuscleById(id)
        }
}