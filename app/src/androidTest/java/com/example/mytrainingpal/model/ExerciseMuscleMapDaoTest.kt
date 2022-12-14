package com.example.mytrainingpal.model

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrainingpal.model.daos.ExerciseDao
import com.example.mytrainingpal.model.daos.ExerciseMuscleMapDao
import com.example.mytrainingpal.model.daos.MuscleDao
import com.example.mytrainingpal.model.entities.Exercise
import com.example.mytrainingpal.model.entities.ExerciseMuscleMap
import com.example.mytrainingpal.model.entities.Muscle
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

@RunWith(AndroidJUnit4::class)
class ExerciseMuscleMapDaoTest {
    // Run tasks synchronously by Jose Alcérreca. See TestingUtils.kt.
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var exerciseMuscleMapDao: ExerciseMuscleMapDao
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var muscleDao: MuscleDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java
        ).build()
        exerciseMuscleMapDao = db.getExerciseMuscleMapDao()
        exerciseDao = db.getExerciseDao()
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
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        val exerciseMuscleMap = ExerciseMuscleMap(exerciseId, muscleId)
        val exerciseMuscleMapId = exerciseMuscleMapDao.insert(exerciseMuscleMap)
        val exerciseMuscleMapRet = exerciseMuscleMapDao.getExerciseMuscleMapByExerciseId(exerciseId)
        assertThat(exerciseMuscleMapRet, equalTo(exerciseMuscleMap))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        val muscle = Muscle(null, "Biceps")
        val muscleId = muscleDao.insert(muscle)
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        val exerciseMuscleMap = ExerciseMuscleMap(exerciseId, muscleId)
        val exerciseMuscleMapId = exerciseMuscleMapDao.insert(exerciseMuscleMap)
        val exerciseMuscleMapRet = exerciseMuscleMapDao.getExerciseMuscleMapByExerciseId(exerciseId)
        assertThat(exerciseMuscleMapRet, equalTo(exerciseMuscleMap))
        var allMaps = exerciseMuscleMapDao.getAllExerciseMuscleMaps().getOrAwaitValue()
        assertEquals(1, allMaps.size)
        exerciseMuscleMapDao.deleteExerciseMuscleMap(exerciseMuscleMap)
        allMaps = exerciseMuscleMapDao.getAllExerciseMuscleMaps().getOrAwaitValue()
        assertEquals(0, allMaps.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        val muscle = Muscle(null, "Biceps")
        val muscle2 = Muscle(null, "Triceps")
        val muscleId = muscleDao.insert(muscle)
        val muscle2Id = muscleDao.insert(muscle2)
        val exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        val exerciseMuscleMap = ExerciseMuscleMap(exerciseId, muscleId)
        val exerciseMuscleMapId = exerciseMuscleMapDao.insert(exerciseMuscleMap)
        var exerciseMuscleMapRet = exerciseMuscleMapDao.getExerciseMuscleMapByExerciseId(exerciseId)
        assertThat(exerciseMuscleMapRet, equalTo(exerciseMuscleMap))
        exerciseMuscleMapDao.deleteExerciseMuscleMap(exerciseMuscleMap)
        exerciseMuscleMap.muscleIdMap = muscle2Id
        exerciseMuscleMapDao.insert(exerciseMuscleMap)
        exerciseMuscleMapRet = exerciseMuscleMapDao.getExerciseMuscleMapByExerciseId(exerciseId)
        assertThat(exerciseMuscleMapRet, equalTo(exerciseMuscleMap))
    }


}