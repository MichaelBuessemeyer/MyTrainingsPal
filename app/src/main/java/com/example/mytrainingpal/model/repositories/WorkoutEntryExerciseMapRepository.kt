package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.WorkoutEntryExerciseMapDao
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class WorkoutEntryExerciseMapRepository(private val workoutEntryExerciseMapDao: WorkoutEntryExerciseMapDao) {

    val allWorkoutEntryExerciseMaps: LiveData<List<WorkoutEntryExerciseMap>> =
        workoutEntryExerciseMapDao.getAllWorkoutEntryExerciseMaps()
    val searchResults = MutableLiveData<List<WorkoutEntryExerciseMap>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertWorkoutEntryExerciseMap(newWorkoutEntryExerciseMap: WorkoutEntryExerciseMap) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutEntryExerciseMapDao.insert(newWorkoutEntryExerciseMap)
        }
    }

    fun deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutEntryExerciseMapDao.deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap)
        }
    }

    fun findWorkoutEntryExerciseMapeById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<WorkoutEntryExerciseMap> =
        coroutineScope.async(Dispatchers.IO) {
            return@async workoutEntryExerciseMapDao.getWorkoutEntryExerciseMapByWorkoutId(id)
        }

    fun insertForWorkoutEntryIdAndExerciseId(workoutEntryId: Long, exerciseId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            workoutEntryExerciseMapDao.insertForWorkoutEntryIdAndExerciseId(workoutEntryId, exerciseId, 0, "0", 0)
        }
    }
}