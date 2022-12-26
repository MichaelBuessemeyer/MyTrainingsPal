package com.example.mytrainingpal.screens

import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    TabScreen(tabContent = {
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
        navController = navController)

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
    val allMusclePainEntries by musclePainEntryViewModel.allMusclePainEntries.observeAsState(listOf())
    val maybeTodayMusclePainEntry by musclePainEntryMapViewModel.searchResult.observeAsState(null)
    Log.d("MusclePainScreenContent", "Rerendering MusclePainScreenContent")
    val todayMusclePainEntryId: Long? = remember(allMusclePainEntries) {
        val index = allMusclePainEntries.indexOfFirst { musclePainEntry ->
            musclePainEntry.date.compareTo(todayDate()) == 0
        }
        Log.d("MusclePainScreenContent", "todayMusclePainEntryId = $index")
        if (index > -1) {
            val musclePainEntryId = allMusclePainEntries[index].musclePainEntryId ?: 0
            musclePainEntryMapViewModel.getMusclePainEntryByIdWithMuscles(musclePainEntryId)
            musclePainEntryId
        } else null
    }
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    remember(maybeTodayMusclePainEntry) {
        // "Sideeffect" to load the already existing muscle pain entry of today.
        if (todayMusclePainEntryId != null && maybeTodayMusclePainEntry != null) {
            soreMuscles.clear()
            val todayMusclePainEntry = maybeTodayMusclePainEntry!!
            val soreMuscleCount = todayMusclePainEntry.soreMusclesConnections.size
            Log.d("MusclePainScreenContent", "found sore muscles in db. size is = $soreMuscleCount")
            for ((connection, soreMusclesList) in todayMusclePainEntry.soreMusclesConnections) {
                soreMuscles.add(Pair(soreMusclesList[0], connection.painIntensity))
            }
            todayMusclePainEntry.musclePainEntry.musclePainEntryId
        } else {
            null
        }

    }
    MusclePainWidget(soreMuscles = soreMuscles, editable = true,
        addSoreMuscle = { pair -> soreMuscles.add(pair) },
        setSoreMuscle = { index, pair -> soreMuscles[index] = pair },
        removeSoreMuscle = { index -> soreMuscles.removeAt(index) })
    FloatingActionButton(onClick = {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch(Dispatchers.IO) {
            var musclePainEntryId = todayMusclePainEntryId
            if (musclePainEntryId == null) {
                musclePainEntryId = musclePainEntryViewModel.insertMusclePainEntryOnCurrentThread(
                    MusclePainEntry(date = todayDate())
                )
            } else {
                musclePainEntryMapViewModel.deleteAllSoreMusclesForMusclePainEntry(musclePainEntryId)
            }

            for ((muscle, painIntensity) in soreMuscles) {
                Log.d(
                    "MusclePainScreenContent",
                    "Saving muscle ${muscle.name} with soreness $painIntensity."
                )
                musclePainEntryMapViewModel.insertForMusclePainEntryIdAndMuscleName(
                    musclePainEntryId,
                    muscle.name,
                    painIntensity
                )
            }
        }

        navigateToHome()
    }) {
        Icon(
            imageVector = Icons.Default.Check,
            tint = MaterialTheme.colors.onSecondary,
            contentDescription = "Save"
        )
    }
}