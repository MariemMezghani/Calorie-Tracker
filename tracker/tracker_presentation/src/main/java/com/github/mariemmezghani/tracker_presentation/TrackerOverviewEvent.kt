package com.github.mariemmezghani.tracker_presentation

import com.github.mariemmezghani.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {

    object OnSelectNextDay:TrackerOverviewEvent()
    object OnSelectPreviousDay:TrackerOverviewEvent()
    data class OnExpandMeal(val meal:Meal):TrackerOverviewEvent()
    data class OnDeleteFood(val food:TrackedFood):TrackerOverviewEvent()
    data class OnAddFoodClick(val meal:Meal):TrackerOverviewEvent()
}