package com.example.mytrainingpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.mytrainingpal.composables.AppNavHost
import com.example.mytrainingpal.ui.theme.MyTrainingPalTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // The ui.
        setContent {
            val navController = rememberNavController()
            MyTrainingPalTheme {
                AppNavHost(navController = navController)
            }
        }
    }
}