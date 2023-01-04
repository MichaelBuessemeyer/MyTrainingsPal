package com.example.mytrainingpal.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.R
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.prefrences.PreferencesConstants
import com.example.mytrainingpal.prefrences.PreferencesConstants.DAYS
import com.example.mytrainingpal.prefrences.PreferencesViewModel
import java.io.File

@Composable
fun SettingsScreen(
    navController: NavController,
    preferencesViewModel: PreferencesViewModel
) {
    val preferencesState by preferencesViewModel.allPreferences.collectAsState(mapOf<String, Any>())
    var preferences: Map<String, Any> by remember { mutableStateOf(preferencesState) }

    var userName: String by remember { mutableStateOf(preferencesState[PreferencesConstants.USERNAME_KEY.name].toString()) }
    var age by remember {
        mutableStateOf(
            (preferencesState[PreferencesConstants.AGE_KEY.name] ?: 20) as Int
        )
    }
    var notifyDays by remember {
        mutableStateOf(
            (preferencesState[PreferencesConstants.NOTIFICATION_DAYS_KEY.name]
                ?: setOf<String>()) as Set<*>
        )
    }
    var notifyTime: String by remember { mutableStateOf(preferencesState[PreferencesConstants.NOTIFICATION_TIME_KEY.name].toString()) }
    var defaultBreak by remember {
        mutableStateOf(
            (preferencesState[PreferencesConstants.DEFAULT_BREAK_KEY.name] ?: 30) as Int
        )
    }

    remember(preferencesState) {
        preferences = preferencesState
        userName = preferences[PreferencesConstants.USERNAME_KEY.name].toString()
        age = (preferences[PreferencesConstants.AGE_KEY.name] ?: 20) as Int
        notifyDays = (preferences[PreferencesConstants.NOTIFICATION_DAYS_KEY.name]
            ?: setOf<String>()) as Set<*>
        notifyTime = (preferences[PreferencesConstants.NOTIFICATION_TIME_KEY.name] ?: "12:00") as String
        defaultBreak = (preferences[PreferencesConstants.DEFAULT_BREAK_KEY.name] ?: 30) as Int
        preferences
    }

    TabScreen(
        tabContent = {
            MainSettingsScreenContent(userName = userName,
                updateName = {
                    //preferencesViewModel.setName(it)
                    userName = it
                },
                age = age,
                updateAge = { age = it },
                notificationTime = notifyTime,
                updateNotificationTime = { notifyTime = it },
                defaultBreak = defaultBreak,
                updateDefaultBreak = { defaultBreak = it })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                preferencesViewModel.setName(userName)
                preferencesViewModel.setAge(age)
                preferencesViewModel.setNotificationDays(notifyDays.map { it.toString() }.toSet())
                preferencesViewModel.setNotificationTime(notifyTime)
                preferencesViewModel.setDefaultBreak(defaultBreak)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Save"
                )
            }
        },
        withNavBar = false,
        topBarTitle = Screen.Settings.label,
        topBarIcon = Screen.Settings.icon,
        navController = navController
    )
}

@Composable
fun MainSettingsScreenContent(
    userName: String,
    updateName: (String) -> Unit,
    age: Int,
    updateAge: (Int) -> Unit,
    notificationTime: String,
    updateNotificationTime: (String) -> Unit,
    defaultBreak: Int,
    updateDefaultBreak: (Int) -> Unit
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val image = painterResource(R.drawable.klauskiste)
    val days: List<String> = DAYS

    var daySelection: MutableList<Pair<String, Boolean>> =
        MutableList<Pair<String, Boolean>>(7) { index -> Pair(days[index], false) }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        //TODO Does not work yet. It doesn't find the file even though it exists
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
            onClick = { }, //TODO: promt to change profile picture
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.secondary
            ),
        ) {
            Text("Change My Profile Picture")
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "My Name:")
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "My Age:")
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            SlimTextInput(
                value = userName,
                onValueChange = updateName,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.background(
                    MaterialTheme.colors.primary,
                    shape = RoundedCornerShape(5.dp)
                )
            )
            CustomNumberInput(
                value = age,
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
            CustomTimeInput(value = notificationTime, onValueChange = updateNotificationTime)
            /*CustomNumberInput(
                value = notificationTime,
                onValueChange = updateNotificationTime,
                possibleValues = (1..25).toList(),
                backgroundColor = MaterialTheme.colors.primary,
                postText = "o'Clock"
            )*/
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


