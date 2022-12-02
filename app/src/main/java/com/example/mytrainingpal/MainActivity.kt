package com.example.mytrainingpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mytrainingpal.components.AppNavHost
import com.example.mytrainingpal.components.BottomNavBar
import com.example.mytrainingpal.components.ExerciseFloatingButton
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.ui.theme.MyTrainingPalTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyTrainingPalTheme {
                AppNavHost(navController = navController)
            }

        }
    }
}

@Composable
fun TabScreen(tabContent: @Composable () -> Unit, navController: NavController){
    Scaffold(
        content = { tabContent() },
        floatingActionButton = { ExerciseFloatingButton { navController.navigate(Screen.TrainingMain.route) } },
        bottomBar = { BottomNavBar(navController) }
    )
}

@Composable
fun HomeScreen(navController: NavController) {
    TabScreen(tabContent = { Greeting(name = "Home") }, navController = navController)
}

@Composable
fun MusclePainScreen(navController: NavController) {
    TabScreen({ Greeting(name = "Muscle Pain") }, navController = navController)

}

@Composable
fun TrainingScreen(navController: NavController) {
    TabScreen(tabContent = { Greeting(name = "Training") }, navController = navController)
}

@Composable
fun CalendarScreen(navController: NavController) {
    TabScreen(tabContent = { Greeting(name = "Calendar") }, navController = navController)
}

@Composable
fun SettingsScreen(navController: NavController) {
    Greeting(name = "Settings")
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