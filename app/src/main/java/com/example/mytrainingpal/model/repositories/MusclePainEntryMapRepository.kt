package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.daos.MusclePainEntryMapDao
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class MusclePainEntryMapRepository(private val musclePainEntryMapDao: MusclePainEntryMapDao) {

    val allMusclePainEntryMaps: LiveData<List<MusclePainEntryMap>> =
        musclePainEntryMapDao.getAllSoreMuscleEntryMaps()
    val searchResults = MutableLiveData<List<MusclePainEntryMap>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertMusclePainEntryMap(newMusclePainEntryMap: MusclePainEntryMap) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryMapDao.insert(newMusclePainEntryMap)
        }
    }

    fun deleteMusclePainEntryMap(musclePainEntryMap: MusclePainEntryMap) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryMapDao.deleteMusclePainEntryMap(musclePainEntryMap)
        }
    }

    fun findMusclePainEntryMapById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(asyncFindById(id).await())
        }
    }

    private fun asyncFindById(id: Long): Deferred<MusclePainEntryMap> =
        coroutineScope.async(Dispatchers.IO) {
            return@async musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(id)
        }
}