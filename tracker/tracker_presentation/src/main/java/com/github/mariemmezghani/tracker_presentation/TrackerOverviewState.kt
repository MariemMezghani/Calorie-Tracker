package com.github.mariemmezghani.tracker_presentation

import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import java.time.LocalDate

data class TrackerOverviewState(
    val totalCalories: Int = 0,
    val totalCarb: Int = 0,
    val totalProtein: Int = 0,
    val totalFat: Int = 0,
    val caloriesGoal: Int = 0,
    val carbGoal: Int = 0,
    val proteinGoal: Int = 0,
    val fatGoal: Int = 0,
    val trackedFoodList: List<TrackedFood> = emptyList(),
    val mealList: List<Meal> = defaultList,
    val date :LocalDate = LocalDate.now()
)