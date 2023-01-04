package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.WorkoutEntryDao
import com.example.mytrainingpal.model.entities.WorkoutEntry
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class WorkoutEntryRepository(private val workoutEntryDao: WorkoutEntryDao) {

    val allWorkouts: LiveData<List<WorkoutEntry>> = workoutEntryDao.getAllWorkoutEntries()
    val searchResults = MutableLiveData<List<WorkoutEntry>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertWorkoutEntry(newWorkoutEntry: WorkoutEntry): Long {
        var id: Long = 0
        coroutineScope.launch(Dispatchers.IO) {
            id = workoutEntryDao.insert(newWorkoutEntry)
        }
        return id
    }

    fun insertWorkoutEntryOnCurrentThread(newWorkoutEntry: WorkoutEntry): Long {
        return workoutEntryDao.insert(newWorkoutEntry)
    }

    fun deleteWorkoutEntry(workoutEntry: WorkoutEntry) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutEntryDao.deleteWorkoutEntry(workoutEntry)
        }
    }

    fun findWorkoutEntryById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<WorkoutEntry> =
        coroutineScope.async(Dispatchers.IO) {
            return@async workoutEntryDao.getWorkoutEntryById(id)
        }
}