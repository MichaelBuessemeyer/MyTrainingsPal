package com.example.mytrainingpal.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrainingpal.model.daos.MuscleDao
import com.example.mytrainingpal.model.entities.Muscle
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MuscleDaoTest {
    private lateinit var muscleDao: MuscleDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java).build()
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
        val muscleRet = muscleDao.getMuscleById(muscleId)
        assertThat(muscleRet, equalTo(muscle.copy(muscleId = muscleId)))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        var muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        muscle = muscle.copy(muscleId = muscleId)
        val muscleRet = muscleDao.getMuscleById(muscleId)
        assertThat(muscleRet, equalTo(muscle))
        muscleDao.deleteMuscle(muscle)
        val allMuscles = muscleDao.getAllMuscles()
        assertEquals(0, allMuscles.value!!.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        var muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        muscle = muscle.copy(muscleId = muscleId)
        var muscleRet = muscleDao.getMuscleById(muscleId)
        assertThat(muscleRet, equalTo(muscle))
        val updateName = "Triceps"
        muscle.name = updateName
        muscleDao.updateMuscle(muscle)
        muscleRet = muscleDao.getMuscleById(muscleId)
        assertThat(updateName, equalTo(muscleRet.name))
    }


}