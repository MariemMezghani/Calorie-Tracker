package com.github.mariemmezghani.tracker_domain.usecases

import com.github.mariemmezghani.tracker_domain.model.Mealtype
import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(private val repository: TrackerRepository) {

    suspend operator fun invoke(food: TrackableFood, type: Mealtype, amount: Int, date: LocalDate) {

        return repository.insertFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carb100g / 100f) * amount).roundToInt(),
                protein = ((food.protein100g / 100f) * amount).roundToInt(),
                fat = ((food.protein100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                type = type,
                amount = amount,
                date = date,
                calories = ((food.calories100g / 100f)).roundToInt(),


                )
        )


    }
}