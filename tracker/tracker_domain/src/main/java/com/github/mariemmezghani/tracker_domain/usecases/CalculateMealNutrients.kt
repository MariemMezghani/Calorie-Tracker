package com.github.mariemmezghani.tracker_domain.usecases

import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.domain.model.GoalType
import com.github.mariemmezghani.core.domain.model.UserInfo
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.tracker_domain.model.Mealtype
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(private val preferences: Preferences) {

    operator fun invoke(trackedFoods: List<TrackedFood>):Result {

        val allNutrients = trackedFoods
            .groupBy { it.type }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    calories = foods.sumOf { it.calories },
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    type = type
                )

            }
        val totalCalories= allNutrients.values.sumOf { it.calories }
        val totalCarbs= allNutrients.values.sumOf { it.carbs }
        val totalProtein= allNutrients.values.sumOf { it.protein }
        val totalFat= allNutrients.values.sumOf { it.fat }

        val userInfo= preferences.loadUserInfo()

        val caloriesGoal= dailyCaloryRequirement(userInfo)
        val carbsGoal= (caloriesGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal= (caloriesGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal= (caloriesGoal * userInfo.fatRatio/ 9f).roundToInt()

        return Result(
            caloriesGoal=caloriesGoal,
            fatGoal=fatGoal,
            proteinGoal=proteinGoal,
            carbGoal=carbsGoal,
            calories = totalCalories,
            fat = totalFat,
            carb = totalCarbs,
            protein = totalProtein,
            meal = allNutrients

        )

    }

    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

    data class MealNutrients(
        val calories: Int,
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val type: Mealtype
    )

    data class Result(
        val caloriesGoal: Int,
        val fatGoal: Int,
        val proteinGoal: Int,
        val carbGoal: Int,
        val calories: Int,
        val fat: Int,
        val protein: Int,
        val carb: Int,
        val meal: Map<Mealtype, MealNutrients>
    )
}