package com.example.mytrainingpal.model

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mytrainingpal.model.daos.ExerciseDao
import com.example.mytrainingpal.model.entities.Exercise
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
class ExerciseDaoTest {
    // Run tasks synchronously by Jose Alcérreca. See TestingUtils.kt.
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var exerciseDao: ExerciseDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java
        ).build()
        exerciseDao = db.getExerciseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testInsert() {
        var exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        val exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        var exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        val exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
        exerciseDao.deleteExercise(exercise)
        val allExercises = exerciseDao.getAllExercises().getOrAwaitValue()
        assertEquals(0, allExercises.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        var exercise = Exercise(null, "Biceps Curls", "exercise_1")
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        var exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
        val newGifPath = "exercise_2"
        exercise.pathToGif = newGifPath
        exerciseDao.updateExercise(exercise)
        exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(newGifPath, equalTo(exerciseRet.pathToGif))
    }


}