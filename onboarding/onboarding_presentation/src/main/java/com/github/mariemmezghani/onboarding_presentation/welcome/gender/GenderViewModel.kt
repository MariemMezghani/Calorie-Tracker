package com.github.mariemmezghani.onboarding_presentation.welcome.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenderViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {


    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    private val _uiEvent = Channel<UiEvent.Navigation>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun selectGender(gender: Gender) {
        selectedGender = gender
    }

    fun onNextClicked(){
        preferences.saveGender(selectedGender)
        viewModelScope.launch{
            _uiEvent.send(UiEvent.Navigation(Route.AGE))
        }
    }


}