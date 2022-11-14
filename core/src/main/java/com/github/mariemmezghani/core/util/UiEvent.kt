package com.github.mariemmezghani.core.util

sealed class UiEvent {

    data class Navigation(val route:String): UiEvent()
    data class ShowSnackbar (val message:UiText): UiEvent()

}