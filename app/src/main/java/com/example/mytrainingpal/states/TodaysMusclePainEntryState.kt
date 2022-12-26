package com.example.mytrainingpal.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.view_models.MusclePainEntryViewModel
import com.example.mytrainingpal.util.todayDate


// A composable serving as a state that returns today's MusclePainEntry if there is one.
@Composable
fun rememberTodaysMusclePainEntryState(musclePainEntryViewModel: MusclePainEntryViewModel): MusclePainEntry? {
    val allMusclePainEntries by musclePainEntryViewModel.allMusclePainEntries.observeAsState(listOf())
    val todayMusclePainEntryId: MusclePainEntry? = remember(allMusclePainEntries) {
        val today = todayDate()
        val index = allMusclePainEntries.indexOfFirst { musclePainEntry ->
            musclePainEntry.date.compareTo(today) == 0
        }
        if (index > -1) {
            allMusclePainEntries[index]
        } else null
    }
    return todayMusclePainEntryId
}
