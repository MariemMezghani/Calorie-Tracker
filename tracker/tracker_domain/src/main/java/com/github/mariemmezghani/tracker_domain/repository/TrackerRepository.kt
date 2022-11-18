package com.github.mariemmezghani.tracker_domain.repository

import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(query:String, page:Int, pageSize:Int):Result<List<TrackableFood>>
    suspend fun insertFood(food: TrackedFood)
    suspend fun deleteFood(food: TrackedFood)
    suspend fun getFoodOfDay(date: LocalDate): Flow<List<TrackedFood>>

}