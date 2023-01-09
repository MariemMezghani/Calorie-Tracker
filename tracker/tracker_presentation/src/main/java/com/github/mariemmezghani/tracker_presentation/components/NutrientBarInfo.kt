package com.github.mariemmezghani.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.mariemmezghani.core.R


@Composable
fun NutrientBarInfo(
    name: String,
    color: Color,
    value: Int,
    goal: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {

    val background = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error

    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = angleRatio) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                (value / goal).toFloat()
            } else 0f

        )
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        Canvas(modifier = Modifier.fillMaxSize().aspectRatio(1f)) {

            drawArc(
                startAngle = 90f,
                sweepAngle = 360f,
                color = if (value <= goal) {
                    background
                } else goalExceededColor,
                size = size,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                startAngle = 90f,
                sweepAngle = if (value <= goal) {
                    angleRatio.value * 360
                } else 0f,
                color = color,
                size = size,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )


        }
        Column(
            modifier= modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            UnitDisplay(
                amount= value,
                unit = stringResource(R.string.grams),
                amountColor = if (value<=goal){ MaterialTheme.colors.onPrimary}else goalExceededColor,
                unitTextColor = if (value<=goal){ MaterialTheme.colors.onPrimary}else goalExceededColor,
                modifier = modifier

            )
            Text(
                text = name,
                modifier= modifier,
                color= if (value<=goal){ MaterialTheme.colors.onPrimary}else goalExceededColor,
                style = MaterialTheme.typography.body1,
                fontWeight= FontWeight.Light
            )

        }
    }


}