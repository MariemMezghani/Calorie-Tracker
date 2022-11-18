package com.github.mariemmezghani.tracker_data.mapper


import com.github.mariemmezghani.tracker_data.local.TrackedFoodEntity
import com.github.mariemmezghani.tracker_domain.model.Mealtype
import com.github.mariemmezghani.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.mapToTrackedFood():TrackedFood{
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl= imageUrl,
        type= Mealtype.fromString(type),
        amount=amount,
        date= LocalDate.of(year, month, dayOfMonth),
        calories=calories,
        id=id
    )
    }
fun TrackedFood.mapToTrackedFoodEntity():TrackedFoodEntity{
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl= imageUrl,
        type= type.type,
        amount=amount,
        dayOfMonth = date.dayOfMonth,
        month= date.monthValue,
        year=date.year,
        calories=calories,
        id=id
    )
}