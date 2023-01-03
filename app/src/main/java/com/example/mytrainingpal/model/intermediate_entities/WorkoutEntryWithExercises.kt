package com.example.mytrainingpal.model.intermediate_entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mytrainingpal.model.entities.WorkoutEntry
import com.example.mytrainingpal.model.entities.WorkoutEntryExerciseMap
import com.example.mytrainingpal.util.TrainingStats
import java.util.concurrent.TimeUnit


data class WorkoutEntryWithExercises(
    @Embedded
    val workoutEntry: WorkoutEntry,
    @Relation(
        entity = WorkoutEntryExerciseMap::class,
        parentColumn = "workoutId",
        entityColumn = "workoutIdMap",
    )
    val exerciseConnections: List<WorkoutEntryExerciseConnection>
) {

    fun calculateStats(): TrainingStats {
        var totalWeightLifted = 0
        for ((workoutEntryToExerciseConnection) in exerciseConnections) {
            val weightLifted = workoutEntryToExerciseConnection.reps.split(",")
                .sumOf { it.toInt() * workoutEntryToExerciseConnection.weight.toInt() }
            totalWeightLifted += weightLifted
        }
        return TrainingStats(workoutEntry.duration, totalWeightLifted, workoutEntry.date)
    }
}
