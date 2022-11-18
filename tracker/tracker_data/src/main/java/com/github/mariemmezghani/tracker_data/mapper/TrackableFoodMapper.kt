package com.github.mariemmezghani.tracker_data.mapper

import com.github.mariemmezghani.tracker_data.remote.dto.Product
import com.github.mariemmezghani.tracker_domain.model.TrackableFood

fun Product.mapToTrackableFood(): TrackableFood? {

    return TrackableFood(
        name = productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        carb100g = nutriments.carbohydrates100g.toInt(),
        protein100g = nutriments.proteins100g.toInt(),
        fat100g = nutriments.fat100g.toInt(),
        calories100g = nutriments.energyKcal100g.toInt(),


    )

}