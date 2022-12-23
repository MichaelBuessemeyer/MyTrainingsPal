package com.example.mytrainingpal.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.components.TabScreen


@Composable
fun SettingsScreen(navController: NavController) {
    var nameOfUser by remember { mutableStateOf("Klaus Kiste") }
    var ageOfUser by remember { mutableStateOf(21) }
    TabScreen(tabContent = {
        MainSettingsScreenContent(
            nameOfUser = nameOfUser,
            updateName = { inputValue: String -> nameOfUser = inputValue },
            ageOfUser = ageOfUser,
            updateAge = { inputValue: Int -> ageOfUser = inputValue })
    },navController = navController)
}

        @Composable
        fun MainSettingsScreenContent(nameOfUser: String, updateName: (String) -> Unit, ageOfUser: Int, updateAge: (Int) -> Unit) {
            val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .blur(if (showDialog) 5.dp else 0.dp)
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "My Name:")

                    TextField(
                        value = nameOfUser,
                        onValueChange = { value -> updateName(value) },
                        modifier = Modifier.width(200.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                }
                    //IconButton(onClick = { setShowDialog(true) }) {
                    //    Icon(
                    //        imageVector = Icons.Default.Edit,
                    //        contentDescription = "",
                    //        tint = MaterialTheme.colors.secondary
                    //    )
                    //}
                }
                Row(verticalAlignment = Alignment.Top) {
                    Text(text = "My Age:")
                    TextField(
                        value = ageOfUser.toString(),
                        onValueChange = { value -> updateAge(value.toInt()) },
                        modifier = Modifier.width(200.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
            }
            //NameInputDialog(showDialog, setShowDialog, nameOfUser, updateName)
        }
