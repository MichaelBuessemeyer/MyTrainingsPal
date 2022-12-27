package com.example.mytrainingpal.model

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mytrainingpal.model.daos.*
import com.example.mytrainingpal.model.entities.*
import java.util.*
import java.util.concurrent.Executors


@Database(
    entities = [MusclePainEntry::class, Muscle::class, MusclePainEntryMap::class, Exercise::class, ExerciseMuscleMap::class, WorkoutEntry::class, WorkoutEntryExerciseMap::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class TheMuscleBase : RoomDatabase() {

    abstract fun getMuscleDao(): MuscleDao
    abstract fun getMusclePainEntryDao(): MusclePainEntryDao
    abstract fun getMusclePainEntryMapDao(): MusclePainEntryMapDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getExerciseMuscleMapDao(): ExerciseMuscleMapDao
    abstract fun getWorkoutEntryDao(): WorkoutEntryDao
    abstract fun getWorkoutEntryExerciseMapDao(): WorkoutEntryExerciseMapDao

    companion object {

        @Volatile
        private var instance: TheMuscleBase? = null

        fun getDatabaseInstance(context: Context): TheMuscleBase {
            synchronized(this) {

                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, TheMuscleBase::class.java, "musclebase.db")
                            // How to add default data to the database taken from Amir's stack overflow answer
                            // https://stackoverflow.com/questions/50520840/what-is-the-proper-way-to-implement-addcallback-when-providing-roomdatabase-v.
                            // prepopulate the database after onCreate was called
                            .addCallback(object : Callback() {
                                @Suppress("UNUSED_VARIABLE")
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)

                                    Executors.newSingleThreadExecutor().execute {
                                        val localInstance = instance as TheMuscleBase
                                        val muscleDao = localInstance.getMuscleDao()
                                        val exerciseDao = localInstance.getExerciseDao()
                                        val exerciseMuscleMapDao =
                                            localInstance.getExerciseMuscleMapDao()
                                        val workoutEntryExerciseMapDao =
                                            localInstance.getWorkoutEntryExerciseMapDao()
                                        val workoutEntryDao =
                                            localInstance.getWorkoutEntryDao()

                                        fun connectExerciseWithMuscles(
                                            exerciseId: Long,
                                            muscleIds: Array<Long>
                                        ) {
                                            for (muscleId: Long in muscleIds) {
                                                exerciseMuscleMapDao.insert(
                                                    ExerciseMuscleMap(
                                                        exerciseId,
                                                        muscleId
                                                    )
                                                )
                                            }
                                        }

                                        fun connectWorkoutEntryWithExercises(
                                            workoutId: Long,
                                            exerciseIds: Array<Long>,
                                        ) {
                                            for (exerciseId in exerciseIds) {
                                                workoutEntryExerciseMapDao.insert(
                                                    WorkoutEntryExerciseMap(
                                                        workoutId,
                                                        exerciseId,
                                                        4,
                                                        "10,10,10,10",
                                                        20
                                                    )
                                                )
                                            }
                                        }

                                        val leftBicepsId =
                                            muscleDao.insert(Muscle(name = "Left Biceps"))
                                        val rightBicepsId =
                                            muscleDao.insert(Muscle(name = "Right Biceps"))
                                        val leftTricepsId =
                                            muscleDao.insert(Muscle(name = "Left Triceps"))
                                        val rightTricepsId =
                                            muscleDao.insert(Muscle(name = "Right Triceps"))
                                        val leftWristId =
                                            muscleDao.insert(Muscle(name = "Left Wrist"))
                                        val rightWristId =
                                            muscleDao.insert(Muscle(name = "Right Wrist"))
                                        val leftDeltoidsId =
                                            muscleDao.insert(Muscle(name = "Left Deltoids"))
                                        val rightDeltoidsId =
                                            muscleDao.insert(Muscle(name = "Right Deltoids"))
                                        val leftTrapeziusId =
                                            muscleDao.insert(Muscle(name = "Left Trapezius"))
                                        val rightTrapeziusId =
                                            muscleDao.insert(Muscle(name = "Right Trapezius"))
                                        val leftLatissimusId =
                                            muscleDao.insert(Muscle(name = "Left Latissimus"))
                                        val rightLatissimusId =
                                            muscleDao.insert(Muscle(name = "Right Latissimus"))
                                        val leftThoracolumbarFasciaId =
                                            muscleDao.insert(Muscle(name = "Left Thoracolumbar Fascia"))
                                        val rightThoracolumbarFasciaId =
                                            muscleDao.insert(Muscle(name = "Right Thoracolumbar Fascia"))
                                        val leftPectoralisId =
                                            muscleDao.insert(Muscle(name = "Left Pectoralis major"))
                                        val rightPectoralisId =
                                            muscleDao.insert(Muscle(name = "Right Pectoralis major"))
                                        val leftObliquesId =
                                            muscleDao.insert(Muscle(name = "Left Obliques"))
                                        val rightObliquesId =
                                            muscleDao.insert(Muscle(name = "Right Obliques"))
                                        val abdominalsId =
                                            muscleDao.insert(Muscle(name = "Abdominals"))
                                        val leftGlutsId =
                                            muscleDao.insert(Muscle(name = "Left Gluts"))
                                        val rightGlutsId =
                                            muscleDao.insert(Muscle(name = "Right Gluts"))
                                        val leftQuadricepsId =
                                            muscleDao.insert(Muscle(name = "Left Quadriceps"))
                                        val rightQuadricepsId =
                                            muscleDao.insert(Muscle(name = "Right Quadriceps"))
                                        val leftHamstringsId =
                                            muscleDao.insert(Muscle(name = "Left Hamstrings"))
                                        val rightHamstringsId =
                                            muscleDao.insert(Muscle(name = "Right Hamstrings"))
                                        val leftAdductorsId =
                                            muscleDao.insert(Muscle(name = "Left Adductors"))
                                        val rightAdductorsId =
                                            muscleDao.insert(Muscle(name = "Right Adductors"))
                                        val leftTensorFasciaeLataeId =
                                            muscleDao.insert(Muscle(name = "Left Tensor Fasciae Latae"))
                                        val rightTensorFasciaeLataeId =
                                            muscleDao.insert(Muscle(name = "Right Tensor Fasciae Latae"))
                                        val leftKneeId =
                                            muscleDao.insert(Muscle(name = "Left Knee"))
                                        val rightKneeId =
                                            muscleDao.insert(Muscle(name = "Right Knee"))
                                        val leftShinFrontId =
                                            muscleDao.insert(Muscle(name = "Left Shin front"))
                                        val rightShinFrontId =
                                            muscleDao.insert(Muscle(name = "Right Shin front"))
                                        val leftCalvesId =
                                            muscleDao.insert(Muscle(name = "Left Calves"))
                                        val rightCalvesId =
                                            muscleDao.insert(Muscle(name = "Right Calves"))
                                        val leftSoleusId =
                                            muscleDao.insert(Muscle(name = "Left Soleus"))
                                        val rightSoleusId =
                                            muscleDao.insert(Muscle(name = "Right Soleus"))
                                        val leftFootFrontId =
                                            muscleDao.insert(Muscle(name = "Left Foot front"))
                                        val rightFootFrontId =
                                            muscleDao.insert(Muscle(name = "Right Foot front"))
                                        val leftFootBackId =
                                            muscleDao.insert(Muscle(name = "Left Foot back"))
                                        val rightFootBackId =
                                            muscleDao.insert(Muscle(name = "Right Foot back"))
                                        val latPullUpId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Lat pull up",
                                                "exercise_1"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            latPullUpId,
                                            arrayOf(
                                                leftBicepsId,
                                                rightBicepsId,
                                                leftLatissimusId,
                                                rightLatissimusId
                                            )
                                        )
                                        val legRaisesId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Legs raises",
                                                "exercise_2"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            legRaisesId,
                                            arrayOf(
                                                abdominalsId,
                                                leftQuadricepsId,
                                                rightQuadricepsId,
                                                leftObliquesId,
                                                rightObliquesId
                                            )
                                        )
                                        val butterflyId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Butterfly",
                                                "exercise_3"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            butterflyId,
                                            arrayOf(
                                                leftDeltoidsId,
                                                rightDeltoidsId,
                                                leftPectoralisId,
                                                rightPectoralisId
                                            )
                                        )
                                        val armRaisesId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Arm Raises",
                                                "exercise_4"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            armRaisesId,
                                            arrayOf(
                                                leftDeltoidsId,
                                                rightDeltoidsId,
                                                leftTricepsId,
                                                rightTricepsId
                                            )
                                        )
                                        val legCurlsId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Leg Curls",
                                                "exercise_5"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            legCurlsId,
                                            arrayOf(
                                                leftHamstringsId,
                                                rightHamstringsId,
                                                leftCalvesId,
                                                rightCalvesId
                                            )
                                        )
                                        val legExtensionId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Leg Extension",
                                                "exercise_6"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            legExtensionId,
                                            arrayOf(leftQuadricepsId, rightQuadricepsId)
                                        )
                                        val deadliftId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Deadlifts with kettlebell",
                                                "exercise_7"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            deadliftId,
                                            arrayOf(
                                                leftWristId,
                                                rightWristId,
                                                leftQuadricepsId,
                                                rightQuadricepsId,
                                                leftGlutsId,
                                                rightGlutsId,
                                                leftTrapeziusId,
                                                rightTrapeziusId,
                                                leftThoracolumbarFasciaId,
                                                rightThoracolumbarFasciaId
                                            )
                                        )
                                        val armLungesId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Lunges",
                                                "exercise_8"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            armLungesId,
                                            arrayOf(
                                                leftGlutsId,
                                                rightGlutsId,
                                                leftHamstringsId,
                                                rightHamstringsId,
                                                leftQuadricepsId,
                                                rightQuadricepsId
                                            )
                                        )
                                        val hammerCurlsId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Hammer Curls",
                                                "exercise_9"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            hammerCurlsId,
                                            arrayOf(leftBicepsId, rightBicepsId)
                                        )
                                        val overheadDumbbellPressId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Overhead dumbbell press",
                                                "exercise_10"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            overheadDumbbellPressId,
                                            arrayOf(
                                                leftTrapeziusId,
                                                rightTrapeziusId,
                                                leftDeltoidsId,
                                                rightDeltoidsId
                                            )
                                        )
                                        val legPressId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Leg Press",
                                                "exercise_11"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            legPressId,
                                            arrayOf(
                                                leftQuadricepsId,
                                                rightQuadricepsId,
                                                leftHamstringsId,
                                                rightHamstringsId,
                                                leftGlutsId,
                                                rightGlutsId
                                            )
                                        )
                                        val seatedCalfRaiseId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Seated Calf Raise",
                                                "exercise_13"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            seatedCalfRaiseId,
                                            arrayOf(leftCalvesId, rightCalvesId)
                                        )
                                        val standingCalfRaisesId = exerciseDao.insert(
                                            Exercise(
                                                null,
                                                "Standing Calf Raise",
                                                "exercise_14"
                                            )
                                        )
                                        connectExerciseWithMuscles(
                                            standingCalfRaisesId,
                                            arrayOf(leftCalvesId, rightCalvesId)
                                        )

                                        val workout1Id = workoutEntryDao.insert(
                                            WorkoutEntry(
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
                                                ""
                                            )
                                        )
                                        val workout2Id = workoutEntryDao.insert(
                                            WorkoutEntry(
                                                null,
                                                GregorianCalendar(2022, Calendar.DECEMBER, 2).time,
                                                GregorianCalendar(
                                                    2022,
                                                    Calendar.DECEMBER,
                                                    2,
                                                    12,
                                                    30
                                                ).time,
                                                GregorianCalendar(
                                                    2022,
                                                    Calendar.DECEMBER,
                                                    2,
                                                    14,
                                                    30
                                                ).time,
                                                ""
                                            )
                                        )
                                        val workout3Id = workoutEntryDao.insert(
                                            WorkoutEntry(
                                                null,
                                                GregorianCalendar(2022, Calendar.DECEMBER, 3).time,
                                                GregorianCalendar(
                                                    2022,
                                                    Calendar.DECEMBER,
                                                    3,
                                                    12,
                                                    30
                                                ).time,
                                                GregorianCalendar(
                                                    2022,
                                                    Calendar.DECEMBER,
                                                    3,
                                                    14,
                                                    30
                                                ).time,
                                                ""
                                            )
                                        )
                                        connectWorkoutEntryWithExercises(
                                            workout1Id,
                                            arrayOf(
                                                legPressId,
                                                overheadDumbbellPressId,
                                                hammerCurlsId
                                            )
                                        )
                                        connectWorkoutEntryWithExercises(
                                            workout2Id,
                                            arrayOf(
                                                legPressId,
                                                overheadDumbbellPressId,
                                                hammerCurlsId
                                            )
                                        )
                                        connectWorkoutEntryWithExercises(
                                            workout3Id,
                                            arrayOf(
                                                legPressId,
                                                overheadDumbbellPressId,
                                                hammerCurlsId
                                            )
                                        )
                                    }
                                }
                            })
                            .allowMainThreadQueries()
                            .build()
                }
                return instance as TheMuscleBase
            }
        }
    }
}