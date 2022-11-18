package com.github.mariemmezghani.tracker_domain.model

data class TrackableFood(
    val name: String,
    val imageUrl: String?,
    val carb100g: Int,
    val protein100g: Int,
    val fat100g: Int,
    val calories100g: Int
)
