package com.example.mytrainingpal.screens

import android.widget.Toast
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.mytrainingpal.components.MusclePainWidget
import com.example.mytrainingpal.components.Screen
import com.example.mytrainingpal.components.TabScreen
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.states.RememberAddingSoreMusclesToList
import com.example.mytrainingpal.states.RememberFetchMusclePainEntryWithMuscles
import com.example.mytrainingpal.states.RememberTodaysMusclePainEntryState
import com.example.mytrainingpal.util.todayDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MusclePainScreen(
    navController: NavController,
    musclePainEntryViewModel: MusclePainEntryViewModel,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel
) {
    val context = LocalContext.current
    val todaysMusclePainEntry = RememberTodaysMusclePainEntryState(musclePainEntryViewModel)
    RememberFetchMusclePainEntryWithMuscles(todaysMusclePainEntry, musclePainEntryMapViewModel)
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    val soreMuscles: SnapshotStateList<Pair<Muscle, Long>> = remember { mutableStateListOf() }
    RememberAddingSoreMusclesToList(musclePainEntryMapViewModel, soreMuscles)

    val saveSoreMuscleAndNavToHome = {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch(Dispatchers.IO) {
            var musclePainEntryId = todaysMusclePainEntry?.musclePainEntryId
            if (musclePainEntryId == null) {
                musclePainEntryId =
                    musclePainEntryViewModel.insertMusclePainEntryOnCurrentThread(
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
            // Update UI in IO Coroutine from: https://stackoverflow.com/questions/59491707/how-to-wait-for-end-of-a-coroutine
            withContext(Dispatchers.Main) {
                //update the UI
                // Show toast from: https://medium.com/smartherd/how-to-create-toast-message-in-jetpack-compose-android-6144c2749ae0
                Toast.makeText(context, "Saved changes.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    TabScreen(
        tabContent = {
            MusclePainWidget(soreMuscles = soreMuscles, editable = true,
                addSoreMuscle = { pair -> soreMuscles.add(pair) },
                setSoreMuscle = { index, pair -> soreMuscles[index] = pair },
                removeSoreMuscle = { index -> soreMuscles.removeAt(index) })
        },
        topBarTitle = Screen.MusclePainMain.label, topBarIcon = Screen.MusclePainMain.icon,
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = { saveSoreMuscleAndNavToHome() }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onSecondary,
                    contentDescription = "Save"
                )
            }
        }
    )

}
