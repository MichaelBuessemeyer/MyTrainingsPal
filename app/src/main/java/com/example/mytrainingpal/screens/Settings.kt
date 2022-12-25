package com.example.mytrainingpal.screens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.mytrainingpal.Greeting
import com.example.mytrainingpal.R
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
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
            }
            Row(verticalAlignment = Alignment.Top) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()

                ) {
                    val image = painterResource(R.drawable.klauskiste)
                    //Step 3 create a box to overlap image and texts
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
                }
                }
            Row(verticalAlignment = Alignment.Top) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {


                    TextField(
                        value = nameOfUser,
                        onValueChange = { value -> updateName(value) },
                        modifier = Modifier.width(200.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                }
            }
            Row(verticalAlignment = Alignment.Top) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextField(
                        value = ageOfUser.toString(),
                        onValueChange = { value -> updateAge(value.toInt()) },
                        modifier = Modifier.width(200.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        }
