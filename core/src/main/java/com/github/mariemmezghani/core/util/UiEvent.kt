package com.github.mariemmezghani.core.util

sealed class UiEvent {

    data class Navigation(val route:String): UiEvent()

}