package com.github.mariemmezghani.onboarding_presentation.welcome.nutrient

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core_ui.LocalSpacing
import com.github.mariemmezghani.onboarding_presentation.welcome.components.ActionButton
import com.github.mariemmezghani.onboarding_presentation.welcome.components.UnitTextField


@Composable
fun NutrientScreen(
    onNavigate: (UiEvent.Navigation) -> Unit,
    viewModel: NutrientViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {


    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigation -> onNavigate(event)
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(event.message.asString(context = context ))
                else -> Unit
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(spacing.spaceLarge)) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))

            UnitTextField(
                value = viewModel.state.carbRatio,
                onValueChange = { carb -> viewModel.onEvent(NutrientEvent.OnCarbEntered(carb)) },
                unit = stringResource(R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
            UnitTextField(
                value = viewModel.state.proteinRatio,
                onValueChange = { protein -> viewModel.onEvent(NutrientEvent.OnProteinEntered(protein)) },
                unit = stringResource(R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
            UnitTextField(
                value = viewModel.state.fatRatio,
                onValueChange =  { fat -> viewModel.onEvent(NutrientEvent.OnFatEntered(fat)) },
                unit = stringResource(R.string.percent_fats)
            )


            }
            ActionButton(
                text = stringResource(R.string.next),
                onClick = { viewModel.onEvent(NutrientEvent.OnNextClicked) },
                modifier = Modifier.align(Alignment.BottomEnd)
            )


    }

}