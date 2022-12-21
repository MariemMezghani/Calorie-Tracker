package com.github.mariemmezghani.tracker_presentation

import androidx.annotation.DrawableRes
import com.github.mariemmezghani.tracker_domain.model.Mealtype
import com.github.mariemmezghani.core.R

data class Meal(
    val name: String,
    val type: Mealtype,
    @DrawableRes val drawable: Int,
    val totalCarbs: Int = 0,
    val totalFat: Int = 0,
    val totalProtein: Int = 0,
    val calories: Int = 0,
    val expanded: Boolean = false
)

val defaultList= listOf<Meal>(
    Meal(name = "Breakfast",
        drawable = R.drawable.breakfast,
        type = Mealtype.Breakfast
    ),
    Meal(name = "Lunch",
        drawable = R.drawable.lunch,
        type = Mealtype.Lunch
    ),
    Meal(name = "Dinner",
        drawable = R.drawable.dinner,
        type = Mealtype.Dinner
    ),
    Meal(name = "Snack",
        drawable = R.drawable.snack,
        type = Mealtype.Snack
    ),
)
