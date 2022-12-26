package com.example.mytrainingpal.model.repositories


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.daos.MusclePainEntryMapDao
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import kotlinx.coroutines.*

// How to implement repository and viewmodel classes taken from
// https://www.answertopia.com/jetpack-compose/a-jetpack-compose-room-database-and-repository-tutorial/

class MusclePainEntryMapRepository(private val musclePainEntryMapDao: MusclePainEntryMapDao) {

    val allMusclePainEntryMaps: LiveData<List<MusclePainEntryMap>> =
        musclePainEntryMapDao.getAllSoreMuscleEntryMaps()
    val searchResults = MutableLiveData<List<MusclePainEntryMap>>()
    val searchResult = MutableLiveData<MusclePainEntryWithMuscles>()
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

    fun deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId: Long) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryMapDao.deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId)
        }
    }

    fun deleteAllSoreMusclesForMusclePainEntryOnCurrentThread(musclePainEntryId: Long) {
        musclePainEntryMapDao.deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId)
    }

    fun findMusclePainEntryMapById(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = listOf(findMusclePainEntryMapOnlyByIdAsync(id).await())
        }
    }

    private fun findMusclePainEntryMapOnlyByIdAsync(id: Long): Deferred<MusclePainEntryMap> =
        coroutineScope.async(Dispatchers.IO) {
            return@async musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(id)
        }

    fun getMusclePainEntryByIdWithMuscles(id: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResult.value = findMusclePainEntryMapWithMusclesByIdAsync(id).await()
        }
    }

    private fun findMusclePainEntryMapWithMusclesByIdAsync(id: Long): Deferred<MusclePainEntryWithMuscles> =
        coroutineScope.async(Dispatchers.IO) {
            return@async musclePainEntryMapDao.getMusclePainEntryByIdWithMuscles(id)
        }

    fun insertForMusclePainEntryIdAndMuscleName(
        musclePainEntryId: Long,
        muscleName: String,
        painIntensity: Long
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            musclePainEntryMapDao.insertForMusclePainEntryIdAndMuscleName(
                musclePainEntryId,
                muscleName,
                painIntensity
            )
        }
    }

}