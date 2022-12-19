package com.github.mariemmezghani.tracker_domain.usecases

data class TrackerUseCases(
    val calculateMealNutrients: CalculateMealNutrients,
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodForDate: GetFoodForDate
)