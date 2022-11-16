package com.github.mariemmezghani.onboarding_presentation.welcome.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.model.GoalType
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GoalViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {


    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UiEvent.Navigation>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalSelected(goalType: GoalType) {
        selectedGoal = goalType
    }

    fun onNextClicked(){
        preferences.saveGoalType(selectedGoal)
        viewModelScope.launch{
            _uiEvent.send(UiEvent.Navigation(Route.NUTRIENT))
        }
    }


}