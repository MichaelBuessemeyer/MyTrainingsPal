package com.example.mytrainingpal.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytrainingpal.R
import com.example.mytrainingpal.components.*
import com.example.mytrainingpal.model.view_models.PreferencesViewModel
import com.example.mytrainingpal.prefrences.PreferencesConstants
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
        println("notifyDays ${preferences[PreferencesConstants.NOTIFICATION_DAYS_KEY.name]}")
        notifyDays = (preferences[PreferencesConstants.NOTIFICATION_DAYS_KEY.name]
            ?: setOf<String>()) as Set<*>
        notifyTime =
            (preferences[PreferencesConstants.NOTIFICATION_TIME_KEY.name] ?: "12:00") as String
        defaultBreak = (preferences[PreferencesConstants.DEFAULT_BREAK_KEY.name] ?: 30) as Int
        preferences
    }

    TabScreen(
        tabContent = {
            MainSettingsScreenContent(userName = userName,
                updateName = {
                    userName = it
                },
                age = age,
                updateAge = { age = it },
                notificationTime = notifyTime,
                updateNotificationTime = { notifyTime = it },
                notificationDays = notifyDays,
                updateNotificationDays = { notifyDays = it },
                defaultBreak = defaultBreak,
                updateDefaultBreak = { defaultBreak = it },
                deleteAllData = { preferencesViewModel.deleteAllData() }, // TODO: also delete all user data from the database
                navigateToHome = { navController.navigate(RouteGroups.HOME.route) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                preferencesViewModel.setName(userName)
                preferencesViewModel.setAge(age)
                preferencesViewModel.setNotificationDays(notifyDays.map { it.toString() }.toSet())
                preferencesViewModel.setNotificationTime(notifyTime)
                preferencesViewModel.setDefaultBreak(defaultBreak)
                navController.navigate(RouteGroups.HOME.route)
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
    notificationDays: Set<*>,
    updateNotificationDays: (Set<String>) -> Unit,
    notificationTime: String,
    updateNotificationTime: (String) -> Unit,
    defaultBreak: Int,
    updateDefaultBreak: (Int) -> Unit,
    deleteAllData: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    val image = painterResource(R.drawable.klauskiste)
    val horizontalPaddingModifier = Modifier.padding(horizontal = 10.dp)

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {

        // TODO Does not work yet. It doesn't find the file even though it exists
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
        modifier = horizontalPaddingModifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "My Name:")
            Spacer(modifier = Modifier.size(17.dp))
            Text(text = "My Age:")
            Spacer(modifier = Modifier.size(22.dp))
            Text(text = "Default Break Time:")
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
            CustomNumberInput(
                value = defaultBreak,
                onValueChange = updateDefaultBreak,
                possibleValues = (5..90).toList(),
                backgroundColor = MaterialTheme.colors.primary,
                postText = "Seconds"
            )
        }
    }
    NotificationSetting(
        time = notificationTime,
        onTimeChange = updateNotificationTime,
        days = notificationDays as Set<String>, // TODO: safe cast
        onDaysChange = updateNotificationDays
    )
    // TODO: warn before deleting everything
    Text(
        text = "Delete all my data!",
        color = MaterialTheme.colors.secondary,
        textDecoration = TextDecoration.Underline,
        modifier = horizontalPaddingModifier.clickable {
            deleteAllData()
            navigateToHome()
        })
}


