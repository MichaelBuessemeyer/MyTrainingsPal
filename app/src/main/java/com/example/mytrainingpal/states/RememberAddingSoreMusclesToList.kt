package com.example.mytrainingpal.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.mytrainingpal.model.entities.Muscle
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel


// A composable serving as a state that returns today's MusclePainEntry if there is one.
@Composable
fun RememberAddingSoreMusclesToList(
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel,
    soreMuscles: SnapshotStateList<Pair<Muscle, Long>>
) {
    val maybeTodayMusclePainEntry by musclePainEntryMapViewModel.searchResult.observeAsState(null)
    // Keeping track of a mutable list of sore muscles with the help of post
    // https://stackoverflow.com/questions/67252538/jetpack-compose-update-composable-when-list-changes.
    remember(maybeTodayMusclePainEntry) {
        // "Sideeffect" to load the already existing muscle pain entry of today.
        if (maybeTodayMusclePainEntry != null) {
            soreMuscles.clear()
            val todayMusclePainEntry = maybeTodayMusclePainEntry!!
            for ((connection, soreMusclesList) in todayMusclePainEntry.soreMusclesConnections) {
                soreMuscles.add(Pair(soreMusclesList[0], connection.painIntensity))
            }
            todayMusclePainEntry.musclePainEntry.musclePainEntryId
        } else {
            null
        }
    }
}
