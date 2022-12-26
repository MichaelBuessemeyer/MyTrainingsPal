package com.example.mytrainingpal.screens

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.mytrainingpal.components.MusclePainWidget
import com.example.mytrainingpal.components.RouteGroups
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.*

@Composable
fun MusclePainScreen(
    navController: NavController,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel
) {
    TabScreen(
        tabContent = {
            MusclePainScreenContent(
                navigateToHome = {
                    navController.navigate(
                        RouteGroups.HOME.route
                    )
                },
                musclePainEntryViewModel = musclePainEntryViewModel,
                musclePainEntryMapViewModel = musclePainEntryMapViewModel,
            )
        }, topBarTitle = Screen.MusclePainMain.label, topBarIcon = Screen.MusclePainMain.icon,
        navController = navController
    )

}

fun todayDate(): Date {
    val todayDate = LocalDateTime.now()
    return GregorianCalendar(
        todayDate.year,
        todayDate.monthValue,
        todayDate.dayOfMonth
    ).time
}

@Composable
fun MusclePainScreenContent(
    navigateToHome: () -> Unit,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel
) {
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)
    RememberFetchMusclePainEntryWithMuscles(todaysMusclePainEntry, musclePainEntryMapViewModel)
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    RememberAddingSoreMusclesToList(musclePainEntryMapViewModel, soreMuscles)

    MusclePainWidget(soreMuscles = soreMuscles, editable = true,
        addSoreMuscle = { pair -> soreMuscles.add(pair) },
        setSoreMuscle = { index, pair -> soreMuscles[index] = pair },
        removeSoreMuscle = { index -> soreMuscles.removeAt(index) })
    FloatingActionButton(onClick = {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch(Dispatchers.IO) {
            var musclePainEntryId = todaysMusclePainEntry?.musclePainEntryId
            if (musclePainEntryId == null) {
                musclePainEntryId = musclePainEntryViewModel.insertMusclePainEntryOnCurrentThread(
                    MusclePainEntry(date = todayDate())
                )
            } else {
                musclePainEntryMapViewModel.deleteAllSoreMusclesForMusclePainEntryOnCurrentThread(
                    musclePainEntryId
                )
            }
            for ((muscle, painIntensity) in soreMuscles) {
                musclePainEntryMapViewModel.insertForMusclePainEntryIdAndMuscleName(
                    musclePainEntryId,
                    muscle.name,
                    painIntensity
                )
            }
        }
        coroutineScope.launch(Dispatchers.Main) { navigateToHome() }
    }) {
        Icon(
            imageVector = Icons.Default.Check,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = "Save"
        )
    }
}