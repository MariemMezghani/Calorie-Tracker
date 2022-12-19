package com.github.mariemmezghani.tracker_domain.usecases

import com.github.mariemmezghani.tracker_domain.model.Mealtype
import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class DeleteTrackedFood(private val repository: TrackerRepository) {

    suspend operator fun invoke(food: TrackedFood) {

        return repository.deleteFood(food)


    }
}