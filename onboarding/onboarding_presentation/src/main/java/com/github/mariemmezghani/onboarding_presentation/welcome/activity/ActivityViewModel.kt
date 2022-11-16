package com.github.mariemmezghani.onboarding_presentation.welcome.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {


    var selectedActivty by mutableStateOf<ActivityLevel>(ActivityLevel.Low)
        private set

    private val _uiEvent = Channel<UiEvent.Navigation>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onActivityLevelSelected(activityLevel: ActivityLevel) {
        selectedActivty = activityLevel
    }

    fun onNextClicked(){
        preferences.saveActivityLevel(selectedActivty)
        viewModelScope.launch{
            _uiEvent.send(UiEvent.Navigation(Route.GOAL))
        }
    }


}