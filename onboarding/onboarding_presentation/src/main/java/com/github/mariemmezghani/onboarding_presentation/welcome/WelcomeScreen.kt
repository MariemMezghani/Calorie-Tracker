package com.github.mariemmezghani.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.onboarding_presentation.welcome.components.ActionButton


@Composable
fun WelcomeScreen(onNavigate: (UiEvent.Navigation) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome_text),
            textAlign= TextAlign.Center,
            style= MaterialTheme.typography.h4

        )
        Spacer(modifier= Modifier.height(16.dp))
        ActionButton(
            text= stringResource(R.string.lets_go),
            onClick = { onNavigate(UiEvent.Navigation(Route.GENDER)) },
            modifier=Modifier.align(Alignment.CenterHorizontally)
        )
    }
}