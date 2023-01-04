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
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    preferences: Preferences,
    val useCases: TrackerUseCases
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set

    private var _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodForDateJob : Job? = null

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
                refreshFood()
            }
            is TrackerOverviewEvent.OnDeleteFood -> {
                viewModelScope.launch {
                    useCases.deleteTrackedFood(event.food)
                }

                refreshFood()
            }

            is TrackerOverviewEvent.OnExpandMeal -> {
                state = state.copy(
                    mealList = state.mealList.map { meal ->
                        if (event.meal.name == meal.name) {
                            meal.copy(expanded = !meal.expanded)

                        } else meal
                    }
                )


            }
            TrackerOverviewEvent.OnSelectNextDay -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshFood()
            }
            TrackerOverviewEvent.OnSelectPreviousDay -> {
                state = state.copy(date = state.date.minusDays(1))
                refreshFood()
            }
        }
    }

    private fun refreshFood() {
        getFoodForDateJob?.cancel()
       getFoodForDateJob=  useCases.getFoodForDate(state.date).onEach { foods ->

            val nutrientsResult = useCases.calculateMealNutrients(foods)

            state = state.copy(

                totalCalories = nutrientsResult.calories,
                totalCarb = nutrientsResult.carb,
                totalFat = nutrientsResult.fat,
                totalProtein = nutrientsResult.protein,
                caloriesGoal = nutrientsResult.caloriesGoal,
                carbGoal = nutrientsResult.carbGoal,
                proteinGoal = nutrientsResult.proteinGoal,
                fatGoal = nutrientsResult.fatGoal,
                trackedFoodList = foods,
                date = state.date,
                mealList = state.mealList.map {

                    val mealNutrients = nutrientsResult.meal[it.type] ?: return@map it.copy(
                        totalCarbs = 0,
                        totalProtein = 0,
                        totalFat = 0,
                        calories = 0
                    )

                    it.copy(
                        totalFat = mealNutrients.fat,
                        totalProtein = mealNutrients.protein,
                        totalCarbs = mealNutrients.carbs,
                        calories = mealNutrients.calories,

                        )

                }
            )

        }.launchIn(viewModelScope)


    }
}