package com.example.mytrainingpal.model

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrainingpal.model.daos.ExerciseDao
import com.example.mytrainingpal.model.daos.WorkoutEntryDao
import com.example.mytrainingpal.model.daos.WorkoutEntryExerciseMapDao
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.utils.getOrAwaitValue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class WorkoutEntryExerciseMapDaoTest {
    // Run tasks synchronously by Jose Alcérreca. See TestingUtils.kt.
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var workoutEntryDao: WorkoutEntryDao
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var workoutEntryExerciseMapDao: WorkoutEntryExerciseMapDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java
        ).build()
        workoutEntryDao = db.getWorkoutEntryDao()
        exerciseDao = db.getExerciseDao()
        workoutEntryExerciseMapDao = db.getWorkoutEntryExerciseMapDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsert() {
        val workoutEntry = WorkoutEntry(
            null,
            GregorianCalendar(2022, Calendar.DECEMBER, 1).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                12,
                30
            ).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                14,
                30
            ).time,
            "",
            null
        )
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        val workoutEntryExerciseMap =
            WorkoutEntryExerciseMap(workoutEntryId, exerciseId, 4, "10,10,10,10", 20)
        val workoutEntryExerciseMapId = workoutEntryExerciseMapDao.insert(workoutEntryExerciseMap)
        val workoutEntryExerciseMapRet =
            workoutEntryExerciseMapDao.getWorkoutEntryExerciseMapByWorkoutId(workoutEntryId)
        assertThat(workoutEntryExerciseMapRet, equalTo(workoutEntryExerciseMap))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        val workoutEntry = WorkoutEntry(
            null,
            GregorianCalendar(2022, Calendar.DECEMBER, 1).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                12,
                30
            ).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                14,
                30
            ).time,
            "",
            null
        )
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        val workoutEntryExerciseMap =
            WorkoutEntryExerciseMap(workoutEntryId, exerciseId, 4, "10,10,10,10", 20)
        val workoutEntryExerciseMapId = workoutEntryExerciseMapDao.insert(workoutEntryExerciseMap)
        val workoutEntryExerciseMapRet =
            workoutEntryExerciseMapDao.getWorkoutEntryExerciseMapByWorkoutId(workoutEntryId)
        assertThat(workoutEntryExerciseMapRet, equalTo(workoutEntryExerciseMap))
        var allMaps = workoutEntryExerciseMapDao.getAllWorkoutEntryExerciseMaps().getOrAwaitValue()
        assertEquals(1, allMaps.size)
        workoutEntryExerciseMapDao.deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap)
        allMaps = workoutEntryExerciseMapDao.getAllWorkoutEntryExerciseMaps().getOrAwaitValue()
        assertEquals(0, allMaps.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        val workoutEntry = WorkoutEntry(
            null,
            GregorianCalendar(2022, Calendar.DECEMBER, 1).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                12,
                30
            ).time,
            GregorianCalendar(
                2022,
                Calendar.DECEMBER,
                1,
                14,
                30
            ).time,
            "",
            null
        )
        val workoutEntryId = workoutEntryDao.insert(workoutEntry)
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        var workoutEntryExerciseMap =
            WorkoutEntryExerciseMap(workoutEntryId, exerciseId, 4, "10,10,10,10", 20)
        val workoutEntryExerciseMapId = workoutEntryExerciseMapDao.insert(workoutEntryExerciseMap)
        var workoutEntryExerciseMapRet =
            workoutEntryExerciseMapDao.getWorkoutEntryExerciseMapByWorkoutId(workoutEntryId)
        assertThat(workoutEntryExerciseMapRet, equalTo(workoutEntryExerciseMap))
        val updatedReps = "10,10,8,8"
        workoutEntryExerciseMap = workoutEntryExerciseMap.copy(reps = updatedReps)
        workoutEntryExerciseMapDao.updateWorkoutEntryExerciseMap(workoutEntryExerciseMap)
        workoutEntryExerciseMapRet =
            workoutEntryExerciseMapDao.getWorkoutEntryExerciseMapByWorkoutId(workoutEntryId)
        assertThat(workoutEntryExerciseMapRet, equalTo(workoutEntryExerciseMap))
    }
}