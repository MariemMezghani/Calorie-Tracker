package com.github.mariemmezghani.tracker_domain.usecases

import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodForDate(private val repository: TrackerRepository) {

    operator fun invoke(date:LocalDate): Flow<List<TrackedFood>> {

        return repository.getFoodOfDay(date)


    }
}