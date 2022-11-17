package com.github.mariemmezghani.onboarding_presentation.welcome


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core_ui.LocalSpacing
import com.github.mariemmezghani.onboarding_presentation.welcome.components.ActionButton
import com.github.mariemmezghani.onboarding_presentation.welcome.components.SelectableButton
import com.github.mariemmezghani.onboarding_presentation.welcome.gender.GenderViewModel


@Composable
fun GenderScreen(
    onNavigate: (UiEvent.Navigation) -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
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

            Text(stringResource(id= R.string.whats_your_gender), style= MaterialTheme.typography.h3)

            Spacer(modifier= Modifier.height(LocalSpacing.current.spaceMedium))

            Row {
                SelectableButton(
                    text = stringResource(R.string.male),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGender is Gender.Male,
                    onClick = {viewModel.selectGender(Gender.Male)} ,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )
                Spacer(Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.female),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGender is Gender.Female,
                    onClick = {viewModel.selectGender(Gender.Female)},
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