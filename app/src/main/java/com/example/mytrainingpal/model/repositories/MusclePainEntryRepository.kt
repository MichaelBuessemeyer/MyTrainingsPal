package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.daos.ExerciseMuscleMapDao
import com.example.mytrainingpal.model.daos.MusclePainEntryDao
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.MusclePainEntry
import kotlinx.coroutines.*
// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class MusclePainEntryRepository(private val musclePainEntryDao: MusclePainEntryDao) {

    val allMusclePainEntries: LiveData<List<MusclePainEntry>> = musclePainEntryDao.getAllMusclePainEntries()
    val searchResults = MutableLiveData<List<MusclePainEntry>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertMusclePainEntry(newMusclePainEntry: MusclePainEntry) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryDao.insert(newMusclePainEntry)
        }
    }

    fun deleteMusclePainEntry(musclePainEntry: MusclePainEntry) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryDao.deleteMusclePainEntry(musclePainEntry)
        }
    }

    fun findMusclePainEntryById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<MusclePainEntry> =
        coroutineScope.async(Dispatchers.IO) {
            return@async musclePainEntryDao.getMusclePainEntryById(id)
        }
}