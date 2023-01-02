package com.github.mariemmezghani.tracker_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.tracker_domain.usecases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    preferences: Preferences,
    val useCases: TrackerUseCases
) : ViewModel() {

    var state by  mutableStateOf(TrackerOverviewState())
        private set

    private var _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()


    init {

        preferences.saveShouldShowOnBoarding(false)

    }


    fun onEvent(event: TrackerOverviewEvent) {

        when (event) {

            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigation(
                            Route.SEARCH
                        )
                    )
                }
            }
            is TrackerOverviewEvent.OnDeleteFood -> {
                viewModelScope.launch {
                    useCases.deleteTrackedFood(event.food)
                }
            }
            is TrackerOverviewEvent.OnExpandMeal -> {
                state= state.copy(
                    mealList = state.mealList.map{ meal ->
                        if (event.meal.name == meal.name){
                            meal.copy(expanded = !meal.expanded)

                        }else meal
                    }
                )


            }
            TrackerOverviewEvent.OnSelectNextDay -> {
                state= state.copy(date = state.date.plusDays(1))
            }
            TrackerOverviewEvent.OnSelectPreviousDay -> {
                state= state.copy(date= state.date.minusDays(1))
            }
        }
    }
}