package com.github.mariemmezghani.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size


@Composable
fun NtrientsBar(
    carbs: Int = 0,
    protein: Int = 0,
    fat: Int = 0,
    calories: Int = 0,
    caloryGoal: Int = 0,
    modifier: Modifier = Modifier
) {

    val background = MaterialTheme.colors.background
    val caloriesExceededError = MaterialTheme.colors.error

    var carbsWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {

        carbsWidthRatio.animateTo(
            targetValue = (carbs * 4f) / calories
        )
    }

    LaunchedEffect(key1 = protein) {

        proteinWidthRatio.animateTo(
            targetValue = (protein * 4f) / calories
        )
    }

    LaunchedEffect(key1 = fat) {

        fatWidthRatio.animateTo(
            targetValue = (fat * 4f) / calories
        )
    }

    Canvas(modifier = modifier) {


        if (calories <= caloryGoal) {


            val carbsWithSize = carbsWidthRatio.value * size.width
            val fatWidthSize = fatWidthRatio.value * size.width
            val proteinWidthSize = proteinWidthRatio.value * size.width

            drawRoundRect(
                size = Size(
                    width = fatWidthSize + carbsWithSize + proteinWidthSize,
                    height = size.height
                ),
                color = caloriesExceededError,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(

                size = Size(
                    width = carbsWithSize + proteinWidthSize,
                    height = size.height
                ),
                color = caloriesExceededError,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(

                size = Size(
                    width = proteinWidthSize,
                    height = size.height
                ),
                color = caloriesExceededError,
                cornerRadius = CornerRadius(100f)
            )
        }

        drawRoundRect(size = size, color = caloriesExceededError, cornerRadius = CornerRadius(100f))


    }

}