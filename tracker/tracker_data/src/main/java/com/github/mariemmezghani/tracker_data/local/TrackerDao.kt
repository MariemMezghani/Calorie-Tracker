package com.github.mariemmezghani.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TrackerDao {


    @Insert
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query("""SELECT * FROM trackedfoodentity WHERE dayOfMonth = :day AND month = :month AND year = :year """)
    fun queryTrackedFood(day: Int, month:Int, year:Int): Flow<List<TrackedFoodEntity>>

}