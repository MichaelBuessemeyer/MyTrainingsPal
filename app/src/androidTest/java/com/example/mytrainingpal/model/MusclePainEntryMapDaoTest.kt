package com.example.mytrainingpal.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class MusclePainEntryMapDaoTest {
    private lateinit var musclePainEntryMapDao: MusclePainEntryMapDao
    private lateinit var musclePainEntryDao: MusclePainEntryDao
    private lateinit var muscleDao: MuscleDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java).build()
        musclePainEntryMapDao = db.getMusclePainEntryMapDao()
        musclePainEntryDao = db.getMusclePainEntryDao()
        muscleDao = db.getMuscleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsert() {
        val muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        var musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        val musclePainEntryMap = MusclePainEntryMap(musclePainEntryId, muscleId, MusclePainEntryMapConstants.MODERATE_PAIN)
        val musclePainEntryMapId = musclePainEntryMapDao.insert(musclePainEntryMap)
        val musclePainEntryMapRet = musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(musclePainEntryId)
        assertThat(musclePainEntryMapRet, equalTo(musclePainEntryMap))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        val muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        var musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        val musclePainEntryMap = MusclePainEntryMap(musclePainEntryId, muscleId, MusclePainEntryMapConstants.MODERATE_PAIN)
        val musclePainEntryMapId = musclePainEntryMapDao.insert(musclePainEntryMap)
        val musclePainEntryMapRet = musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(musclePainEntryMapId)
        assertThat(musclePainEntryMapRet, equalTo(musclePainEntryMap))
        var allMaps = musclePainEntryMapDao.getAllSoreMuscleEntryMaps()
        assertEquals(1, allMaps.size)
        musclePainEntryMapDao.deleteMusclePainEntryMap(musclePainEntryMap)
        allMaps = musclePainEntryMapDao.getAllSoreMuscleEntryMaps()
        assertEquals(0, allMaps.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        val muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        val musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        val musclePainEntryMap = MusclePainEntryMap(musclePainEntryId, muscleId, MusclePainEntryMapConstants.MODERATE_PAIN)
        val musclePainEntryMapId = musclePainEntryMapDao.insert(musclePainEntryMap)
        var musclePainEntryMapRet = musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(musclePainEntryId)
        assertThat(musclePainEntryMapRet, equalTo(musclePainEntryMap))
        val updatedSoreness = MusclePainEntryMapConstants.SEVERE_PAIN
        musclePainEntryMap.painIntensity = updatedSoreness
        musclePainEntryMapDao.updateMusclePainEntryMap(musclePainEntryMap)
        musclePainEntryMapRet = musclePainEntryMapDao.getMusclePainEntryMapByMusclePainEntryIdMap(musclePainEntryMapId)
        assertThat(musclePainEntryMapRet, equalTo(musclePainEntryMap))
    }


}