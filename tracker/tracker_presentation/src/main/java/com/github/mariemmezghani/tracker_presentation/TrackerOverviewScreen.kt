package com.github.mariemmezghani.tracker_presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core_ui.LocalSpacing
import com.github.mariemmezghani.tracker_presentation.components.NutrienHeader

@Composable
fun TrackerOverviewScreen(
    onNavigate : (UiEvent.Navigation) -> Unit,
    viewModel: OverviewViewModel= hiltViewModel()
){
    val state= viewModel.state
    val spacing= LocalSpacing.current

    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(bottom = spacing.spaceMedium)
            ){

        item {
            NutrienHeader(state= state)
        }
    }


}