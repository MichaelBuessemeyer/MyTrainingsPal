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

@RunWith(AndroidJUnit4::class)
class ExerciseDaoTest {
    private lateinit var exerciseDao: ExerciseDao
    private lateinit var db: TheMuscleBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TheMuscleBase::class.java).build()
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
        var exercise = Exercise(null, "Biceps Curls", "res/gifs/Exercise_1.gif",)
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        val exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
    }

    @Test
    @Throws(Exception::class)
    fun testDelete() {
        var exercise = Exercise(null, "Biceps Curls", "res/gifs/Exercise_1.gif",)
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        val exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
        exerciseDao.deleteExercise(exercise)
        val allExercises = exerciseDao.getAllExercises()
        assertEquals(0, allExercises.size)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdate() {
        var exercise = Exercise(null, "Biceps Curls", "res/gifs/Exercise_1.gif",)
        val exerciseId = exerciseDao.insert(exercise)
        exercise = exercise.copy(exerciseId = exerciseId)
        var exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(exerciseRet, equalTo(exercise))
        val newGifPath = "res/gifs/Exercise_2.gif"
        exercise.pathToGif = newGifPath
        exerciseDao.updateExercise(exercise)
        exerciseRet = exerciseDao.getExerciseById(exerciseId)
        assertThat(newGifPath, equalTo(exerciseRet.pathToGif))
    }


}