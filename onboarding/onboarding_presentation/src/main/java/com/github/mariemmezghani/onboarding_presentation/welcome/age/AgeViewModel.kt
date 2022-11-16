package com.github.mariemmezghani.onboarding_presentation.welcome.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AgeViewModel @Inject constructor(private val preferences: Preferences) : ViewModel() {

    var age by mutableStateOf<String>("20")
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun saveAge(text: String) {
        if (text.length <= 3) {
            age = text.filter { it.isDigit() }

        }
    }

    fun onNextClicked() {

        viewModelScope.launch {
            val age = age.toIntOrNull() ?: kotlin.run {

                _uiEvent.send(UiEvent.ShowSnackbar(UiText.ResourceString(R.string.error_age_cant_be_empty)))

                return@launch

            }

            preferences.saveAge(age)
            _uiEvent.send(UiEvent.Navigation(Route.WEIGHT))
        }
    }

}