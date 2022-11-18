package com.github.mariemmezghani.tracker_domain.model

import java.time.LocalDate

data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: Mealtype,
    val amount: Int,
    val date: LocalDate,
    val calories: Int,
    val id: Int?= null
)