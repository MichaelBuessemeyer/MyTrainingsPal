package com.example.mytrainingpal

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mytrainingpal.components.AppNavHost
import com.example.mytrainingpal.model.*
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import com.example.mytrainingpal.ui.theme.MyTrainingPalTheme
import java.util.*


class MainActivity : ComponentActivity() {
    private val TAG = "MyInfoForDebugging"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The the ui.
        setContent {
            val navController = rememberNavController()
            MyTrainingPalTheme {
                AppNavHost(navController = navController)
            }
        }
        // Load some stuff into the db.
        val db = Room.databaseBuilder(
            applicationContext,
            TheMuscleBase::class.java, "TheMuscleBase"
        ).allowMainThreadQueries().build()
        var muscleDao = db.getMuscleDao()
        var musclePainEntryMapDao = db.getMusclePainEntryMapDao()
        var musclePainEntryDao = db.getMusclePainEntryDao()
        var muscleId1 = muscleDao.insert(Muscle(name = "left_wrist"))
        var muscleId2 = muscleDao.insert(Muscle(name = "right_wrist"))
        var muscleId3 = muscleDao.insert(Muscle(name = "left_biceps"))
        var muscleId4 = muscleDao.insert(Muscle(name = "right_biceps"))
        var muscleId5 = muscleDao.insert(Muscle(name = "left_triceps"))
        var muscleId6 = muscleDao.insert(Muscle(name = "right_triceps"))
        var muscleId7 = muscleDao.insert(Muscle(name = "left_shoulder"))
        var muscleId8 = muscleDao.insert(Muscle(name = "right_shoulder"))
        var muscleId9 = muscleDao.insert(Muscle(name = "left_chest"))
        var muscleId10 = muscleDao.insert(Muscle(name = "right_chest"))

        var musclePainEntryId1 =
            musclePainEntryDao.insert(MusclePainEntry(date = GregorianCalendar(2022, Calendar.DECEMBER, 1).time))
        var musclePainEntryId2 =
            musclePainEntryDao.insert(MusclePainEntry(date = GregorianCalendar(2022, Calendar.DECEMBER, 2).time))
        var mappingId1 = musclePainEntryMapDao.insert(MusclePainEntryMap(musclePainEntryId1, muscleId1, 2))
        var mappingId2 = musclePainEntryMapDao.insert(MusclePainEntryMap(musclePainEntryId1, muscleId2, 2))
        var mappingId3 = musclePainEntryMapDao.insert(MusclePainEntryMap(musclePainEntryId1, muscleId3, 2))
        var mappingId4 = musclePainEntryMapDao.insert(MusclePainEntryMap(musclePainEntryId1, muscleId4, 2))

        for(muscle: Muscle in muscleDao.getAllMuscles().value!!) {
            Log.d(TAG,"Muscle is ${muscle.name} with id ${muscle.muscleId}")
        }
        for(musclePainEntry: MusclePainEntry in musclePainEntryDao.getAllMusclePainEntries().value!!) {
            Log.d(TAG,"MusclePainEntry is ${musclePainEntry.date} with id ${musclePainEntry.musclePainEntryId}")
        }
        for(mapping: MusclePainEntryWithMuscles in musclePainEntryMapDao.getAllMusclePainEntriesWithMuscles()){
            Log.d(TAG, "MusclePainEntry on day ${mapping.musclePainEntry.date} has the following pain:")
            for(connection in mapping.soreMusclesConnections){
                Log.d(TAG, "\t\t Pain Value:${connection.musclePainEntryToMuscleConnection.painIntensity}")
                for(muscle in connection.soreMuscles){
                    Log.d(TAG, "\t\t Muscle Name:${muscle.name}")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting(name = "Android")
}