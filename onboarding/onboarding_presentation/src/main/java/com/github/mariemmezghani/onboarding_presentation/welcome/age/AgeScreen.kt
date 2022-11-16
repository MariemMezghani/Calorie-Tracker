package com.github.mariemmezghani.onboarding_presentation.welcome.age

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
fun AgeScreen(
    onNavigate: (UiEvent.Navigation) -> Unit,
    viewModel: AgeViewModel = hiltViewModel(),
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
                stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(16.dp))

            UnitTextField(value=viewModel.age, onValueChange = viewModel::onAgeEntered, unit= stringResource(R.string.years))


            }
            ActionButton(
                text = stringResource(R.string.next),
                onClick = { viewModel.onNextClicked() },
                modifier = Modifier.align(Alignment.BottomEnd)
            )


    }

}