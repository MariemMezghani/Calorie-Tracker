package com.github.mariemmezghani.tracker_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mariemmezghani.calorietracker.ui.theme.CarbColor
import com.github.mariemmezghani.calorietracker.ui.theme.FatColor
import com.github.mariemmezghani.calorietracker.ui.theme.ProteinColor
import com.github.mariemmezghani.core_ui.LocalSpacing
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.tracker_presentation.TrackerOverviewState


@Composable
fun NutrienHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    Column(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp))
            .background(color = MaterialTheme.colors.primary)
            .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            UnitDisplay(
                amount = state.totalCalories,
                unit = stringResource(R.string.kcal),
                amountTextSize = 40.sp,
                amountColor = MaterialTheme.colors.onPrimary,
                unitTextColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )

            UnitDisplay(
                amount = state.caloriesGoal,
                unit = stringResource(R.string.kcal),
                amountTextSize = 40.sp,
                amountColor = MaterialTheme.colors.onPrimary,
                unitTextColor = MaterialTheme.colors.onPrimary
            )

        }

        Spacer(Modifier.height(spacing.spaceMedium))

        NtrientsBar(
            carbs = state.totalCarb,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            caloryGoal = state.caloriesGoal,
            modifier = Modifier.fillMaxWidth().height(40.dp)
        )

        Spacer(Modifier.height(spacing.spaceLarge))

        Row {

            NutrientBarInfo(
                name = stringResource(R.string.fat),
                color = FatColor,
                value = state.totalFat,
                goal = state.fatGoal,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                name = stringResource(R.string.protein),
                color = ProteinColor,
                value = state.totalProtein,
                goal = state.proteinGoal,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                name = stringResource(R.string.carbs),
                color = CarbColor,
                value = state.totalCarb,
                goal = state.totalCarb,
                modifier = Modifier.size(90.dp)
            )


        }

    }
}