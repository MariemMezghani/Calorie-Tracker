package com.github.mariemmezghani.onboarding_presentation.welcome


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.domain.model.GoalType
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core_ui.LocalSpacing
import com.github.mariemmezghani.onboarding_presentation.welcome.activity.ActivityViewModel
import com.github.mariemmezghani.onboarding_presentation.welcome.components.ActionButton
import com.github.mariemmezghani.onboarding_presentation.welcome.components.SelectableButton
import com.github.mariemmezghani.onboarding_presentation.welcome.gender.GenderViewModel
import com.github.mariemmezghani.onboarding_presentation.welcome.goal.GoalViewModel


@Composable
fun GoalScreen(
    onNavigate: (UiEvent.Navigation) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {


    val spacing = LocalSpacing.current

    LaunchedEffect(true){
        viewModel.uiEvent.collect{event ->
            when (event){
                is UiEvent.Navigation -> onNavigate(event)
                else -> Unit
        }
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(spacing.spaceLarge)) {

        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
                ){

            Text(stringResource(id= R.string.lose_keep_or_gain_weight), style= MaterialTheme.typography.h4)

            Spacer(modifier= Modifier.height(16.dp))

            Row {
                SelectableButton(
                    text = stringResource(R.string.lose),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
                    onClick = {viewModel.onGoalSelected(GoalType.LoseWeight)} ,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )
                Spacer(Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.keep),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
                    onClick = {viewModel.onGoalSelected(GoalType.KeepWeight)},
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )
                Spacer(Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.gain),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.GainWeight,
                    onClick = {viewModel.onGoalSelected(GoalType.GainWeight)},
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )
            }


        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = {viewModel.onNextClicked()},
            modifier = Modifier.align(Alignment.BottomEnd)
        )

    }

}