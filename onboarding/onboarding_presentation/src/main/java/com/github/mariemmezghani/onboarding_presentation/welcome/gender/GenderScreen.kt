package com.github.mariemmezghani.onboarding_presentation.welcome


import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

    Box(modifier = Modifier.fillMaxSize().padding(spacing.spaceLarge)) {

        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
                ){

            Text(stringResource(id= R.string.whats_your_gender))

            Row {
                SelectableButton(
                    text = stringResource(R.string.male),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = false,
                    onClick = {viewModel.selectGender(Gender.Male)} ,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )

                SelectableButton(
                    text = stringResource(R.string.female),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    isSelected = false,
                    onClick = {viewModel.selectGender(Gender.Female)},
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight=FontWeight.Normal
                        )
                )
            }

            ActionButton(
                text = stringResource(R.string.next),
                onClick = {onNavigate(UiEvent.Navigation(Route.AGE))},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }


    }

}