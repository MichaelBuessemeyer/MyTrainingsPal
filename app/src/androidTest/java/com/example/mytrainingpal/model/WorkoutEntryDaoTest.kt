package com.example.mytrainingpal.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrainingpal.model.daos.WorkoutEntryDao
import com.example.mytrainingpal.model.entities.WorkoutEntry
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class WorkoutEntryDaoTest {
    private lateinit var workoutEntryDao: WorkoutEntryDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java).build()
        workoutEntryDao = db.getWorkoutEntryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsert() {
        var workoutEntry = WorkoutEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time,"12:12:00","13:30:22","",null)
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        workoutEntry = workoutEntry.copy(workoutId = workoutEntryId)
        val workoutEntryRet = workoutEntryDao.getWorkoutEntryById(workoutEntryId)
        assertThat(workoutEntryRet, equalTo(workoutEntry))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        var workoutEntry = WorkoutEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time,"12:12:00","13:30:22","",null)
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        workoutEntry = workoutEntry.copy(workoutId = workoutEntryId)
        val workoutEntryRet = workoutEntryDao.getWorkoutEntryById(workoutEntryId)
        assertThat(workoutEntryRet, equalTo(workoutEntry))
        workoutEntryDao.deleteWorkoutEntry(workoutEntry)
        val allWorkoutEntries = workoutEntryDao.getAllWorkoutEntries()
        assertEquals(0, allWorkoutEntries.value!!.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        var workoutEntry = WorkoutEntry(null, GregorianCalendar(2022, Calendar.DECEMBER, 1).time,"12:12:00","13:30:22","",null)
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        workoutEntry = workoutEntry.copy(workoutId = workoutEntryId)
        var workoutEntryRet = workoutEntryDao.getWorkoutEntryById(workoutEntryId)
        assertThat(workoutEntryRet, equalTo(workoutEntry))
        val updatedDate = GregorianCalendar(2022, Calendar.DECEMBER, 2).time
        val updatedWorkoutEntry = workoutEntry.copy(date = updatedDate)
        workoutEntryDao.updateWorkoutEntry(updatedWorkoutEntry)
        workoutEntryRet = workoutEntryDao.getWorkoutEntryById(workoutEntryId)
        assertThat(workoutEntryRet, equalTo(updatedWorkoutEntry))
    }
}