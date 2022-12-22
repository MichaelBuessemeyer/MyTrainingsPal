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
class MusclePainEntryDaoTest {
    private lateinit var musclePainEntryDao: MusclePainEntryDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java).build()
        musclePainEntryDao = db.getMusclePainEntryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsert() {
        var musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        musclePainEntry = musclePainEntry.copy(musclePainEntryId = musclePainEntryId)
        val musclePainEntryRet = musclePainEntryDao.getMusclePainEntryById(musclePainEntryId)
        assertThat(musclePainEntryRet, equalTo(musclePainEntry))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        var musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        musclePainEntry = musclePainEntry.copy(musclePainEntryId = musclePainEntryId)
        val musclePainEntryRet = musclePainEntryDao.getMusclePainEntryById(musclePainEntryId)
        assertThat(musclePainEntryRet, equalTo(musclePainEntry))
        musclePainEntryDao.deleteMusclePainEntry(musclePainEntry)
        val allMusclePainEntries = musclePainEntryDao.getAllMusclePainEntries()
        assertEquals(0, allMusclePainEntries.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        var musclePainEntry = MusclePainEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time)
        val musclePainEntryId = musclePainEntryDao.insert(musclePainEntry)
        musclePainEntry = musclePainEntry.copy(musclePainEntryId = musclePainEntryId)
        var musclePainEntryRet = musclePainEntryDao.getMusclePainEntryById(musclePainEntryId)
        assertThat(musclePainEntryRet, equalTo(musclePainEntry))
        val updatedDate = GregorianCalendar(2022, Calendar.DECEMBER, 2).time
        musclePainEntry.date = updatedDate
        musclePainEntryDao.updateMusclePainEntry(musclePainEntry)
        musclePainEntryRet = musclePainEntryDao.getMusclePainEntryById(musclePainEntryId)
        assertThat(updatedDate, equalTo(musclePainEntryRet.date))
    }


}