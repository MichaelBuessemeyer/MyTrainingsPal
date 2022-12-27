package com.example.mytrainingpal.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.mytrainingpal.model.entities.MusclePainEntry
import com.example.mytrainingpal.model.view_models.MusclePainEntryMapViewModel


// A composable serving as a state that returns today's MusclePainEntry if there is one.
@Composable
fun RememberFetchMusclePainEntryWithMuscles(
    todaysMusclePainEntry: MusclePainEntry?,
    musclePainEntryMapViewModel: MusclePainEntryMapViewModel
) {
    remember(todaysMusclePainEntry) {
        if (todaysMusclePainEntry != null) {
            todaysMusclePainEntry.musclePainEntryId?.let { musclePainEntryId ->
                musclePainEntryMapViewModel.getMusclePainEntryByIdWithMuscles(
                    musclePainEntryId
                )
            }
        }
        null
    }
}
