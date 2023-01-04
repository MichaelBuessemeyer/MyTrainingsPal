package com.example.mytrainingpal.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabScreen(
    tabContent: @Composable () -> Unit,
    topBarTitle: String?,
    topBarIcon: ImageVector?,
    navController: NavController,
    withNavBar: Boolean = true,
    floatingActionButton: @Composable () -> Unit = {
        StartExerciseFloatingButton {
            navController.navigate(
                Screen.TrainingMain.route
            )
        }
    },
) {
    Scaffold(
        topBar = {
            if (topBarTitle != null && topBarIcon != null) {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center, modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 16.dp)
                        ) {
                            Icon(
                                topBarIcon,
                                "",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Text(topBarTitle, style = MaterialTheme.typography.h2)
                    }
                }
            }
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                tabContent()
            }
        },
        floatingActionButton = floatingActionButton,
        bottomBar = {
            if (withNavBar) {
                BottomNavBar(navController)
            }
        }
    )
}