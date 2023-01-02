package com.example.mytrainingpal.screens

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import com.example.mytrainingpal.R
import com.example.mytrainingpal.components.CustomNumberInput
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.SlimTextInput
import com.example.mytrainingpal.components.TabScreen
import java.io.File

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "UserData")

@Composable
fun SettingsScreen(
    navController: NavController
) {
    var nameOfUser by remember { mutableStateOf("Klaus Kiste") }
    var ageOfUser by remember { mutableStateOf(21) }
    var timeToTrainUser by remember { mutableStateOf(7) }
    var defaultBreak by remember { mutableStateOf(10) }
    TabScreen(
        tabContent = {
            MainSettingsScreenContent(nameOfUser = nameOfUser,
                updateName = { inputValue: String -> nameOfUser = inputValue },
                ageOfUser = ageOfUser,
                updateAge = { inputValue: Int -> ageOfUser = inputValue },
                timeToTrainUser = timeToTrainUser,
                updateTimeToTrain = { inputValue: Int -> timeToTrainUser = inputValue },
                defaultBreak = defaultBreak,
                updateDefaultBreak = { inputValue: Int -> defaultBreak = inputValue })
        },
        topBarTitle = Screen.Settings.label,
        topBarIcon = Screen.Settings.icon,
        navController = navController
    )
}

@Composable
fun MainSettingsScreenContent(
    nameOfUser: String,
    updateName: (String) -> Unit,
    ageOfUser: Int,
    updateAge: (Int) -> Unit,
    timeToTrainUser: Int,
    updateTimeToTrain: (Int) -> Unit,
    defaultBreak: Int,
    updateDefaultBreak: (Int) -> Unit
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val image = painterResource(R.drawable.klauskiste)
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        //TODO Does not work jet. It doesn't find the file even though it exists
        if (File("/src/main/res/drawable/klauskiste.jpeg").exists()) {
            Box {
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .width(width = 200.dp)
                        .height(height = 200.dp),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            Icon(
                imageVector = Icons.Default.Portrait,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(8.dp)
                    .size(120.dp)
            )
        }
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Text("Change My Profile Picture")
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {


            Text(text = "My Name:")
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {


            SlimTextInput(
                value = nameOfUser,
                onValueChange = updateName,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.background(MaterialTheme.colors.primary)
            )
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(text = "My Age:")
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomNumberInput(
                value = ageOfUser,
                onValueChange = updateAge,
                possibleValues = (1..90).toList(),
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Notify me on")
    }
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.surface),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.surface
            ),
        ) {
            Text("Mo")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Text("Tu")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.surface),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.surface
            ),
        ) {
            Text("We")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Text("Th")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.surface),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.surface
            ),
        ) {
            Text("Fr")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Text("Sa")
        }
        Button(
            border = BorderStroke(1.dp, MaterialTheme.colors.surface),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.surface
            ),
        ) {
            Text("Su")
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(text = "at")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomNumberInput(
                value = timeToTrainUser,
                onValueChange = updateTimeToTrain,
                possibleValues = (1..25).toList(),
                backgroundColor = MaterialTheme.colors.primary,
                postText = "o'Clock"
            )
        }
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(text = "Default break:")
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomNumberInput(
                value = defaultBreak,
                onValueChange = updateDefaultBreak,
                possibleValues = (1..25).toList(),
                backgroundColor = MaterialTheme.colors.primary,
                postText = "Minutes"
            )
        }
    }
}


