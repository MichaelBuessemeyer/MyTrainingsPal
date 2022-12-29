package com.example.mytrainingpal.model.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.model.intermediate_entities.WorkoutEntryWithExercises


@Dao
abstract class WorkoutEntryExerciseMapDao {
    @Insert
    abstract fun insert(workoutEntryExerciseMap: WorkoutEntryExerciseMap): Long

    @Query("SELECT * FROM WorkoutEntryExerciseMap")
    abstract fun getAllWorkoutEntryExerciseMaps(): LiveData<List<WorkoutEntryExerciseMap>>

    @Transaction
    @Query("SELECT * FROM WorkoutEntry")
    abstract fun getAllWorkoutEntriesWithExercises(): LiveData<List<WorkoutEntryWithExercises>>

    @Transaction
    @Query("SELECT * FROM WorkoutEntryExerciseMap WHERE workoutIdMap=:workoutId")
    abstract fun getWorkoutEntryExerciseMapByWorkoutId(workoutId: Long): WorkoutEntryExerciseMap

    @Update
    abstract fun updateWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap)

    @Delete
    abstract fun deleteWorkoutEntryExerciseMap(workoutEntryExerciseMap: WorkoutEntryExerciseMap)

}