package com.github.mariemmezghani.tracker_data.repository

import com.github.mariemmezghani.tracker_data.local.TrackerDao
import com.github.mariemmezghani.tracker_data.mapper.mapToTrackableFood
import com.github.mariemmezghani.tracker_data.mapper.mapToTrackedFood
import com.github.mariemmezghani.tracker_data.mapper.mapToTrackedFoodEntity
import com.github.mariemmezghani.tracker_data.remote.FoodApiInterface
import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(val api:FoodApiInterface, val db:TrackerDao):TrackerRepository{
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
           val result= api.searchFood(query, page, pageSize)
            Result.success(result.products.mapNotNull { it.mapToTrackableFood() })
        }catch (e:Exception){
            Result.failure(e)

        }


    }

    override suspend fun insertFood(food: TrackedFood) {
        db.insertTrackedFood(food.mapToTrackedFoodEntity())
    }

    override suspend fun deleteFood(food: TrackedFood) {
        db.deleteTrackedFood(food.mapToTrackedFoodEntity())
    }

    override suspend fun getFoodOfDay(date: LocalDate):Flow<List<TrackedFood>> {
        return db.queryTrackedFood(date.dayOfMonth, date.monthValue,date.year).map { entities ->
            entities.map { it.mapToTrackedFood() }
        }
    }
}