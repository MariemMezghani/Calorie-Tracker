package com.github.mariemmezghani.onboarding_presentation.welcome.nutrient

sealed class NutrientEvent {
    data class OnCarbEntered(val carbRatio:String):NutrientEvent()
    data class OnProteinEntered(val proteinRatio:String):NutrientEvent()
    data class OnFatEntered(val fatRatio:String):NutrientEvent()
    object OnNextClicked:NutrientEvent()
}