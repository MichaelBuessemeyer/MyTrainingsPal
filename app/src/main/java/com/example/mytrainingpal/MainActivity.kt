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
import com.example.mytrainingpal.model.MusclePainEntryMap
import com.example.mytrainingpal.model.TheMuscleBase
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.intermediate_entities.MusclePainEntryWithMuscles
import com.example.mytrainingpal.ui.theme.MyTrainingPalTheme
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The the ui.
        setContent {
            val navController = rememberNavController()
            MyTrainingPalTheme {
                AppNavHost(navController = navController)
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