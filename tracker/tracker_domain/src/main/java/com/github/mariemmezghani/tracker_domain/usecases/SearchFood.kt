package com.github.mariemmezghani.tracker_domain.usecases

import com.github.mariemmezghani.tracker_domain.model.TrackableFood
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository

class SearchFood(private val repository: TrackerRepository) {

    suspend operator fun invoke(query:String, page: Int = 1, pageSize:Int):Result<List<TrackableFood>>{

        if (query.isBlank()){
            return Result.success(emptyList())
        }

        return repository.searchFood(query.trim(), page,pageSize)


    }
}