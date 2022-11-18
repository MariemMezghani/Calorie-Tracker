package com.github.mariemmezghani.tracker_domain.model

sealed class Mealtype (val type:String){

    object Breakfast: Mealtype("breakfast")
    object Lunch: Mealtype("lunch")
    object Dinner: Mealtype("dinner")
    object Snack: Mealtype("snack")

    companion object{

        fun fromString(type: String):Mealtype{

            return when(type){
                "breakfast" -> Breakfast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> Breakfast
            }
        }
    }
}
