package com.github.mariemmezghani.calorietracker.navigation

import androidx.navigation.NavController
import com.github.mariemmezghani.core.util.UiEvent

fun NavController.navigate(event:UiEvent.Navigation){
    this.navigate(event.route)
}