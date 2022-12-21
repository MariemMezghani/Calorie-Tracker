package com.github.mariemmezghani.tracker_presentation

import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.tracker_domain.usecases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(preferences: Preferences, useCases: TrackerUseCases): ViewModel() {

    init {
        
    }
}