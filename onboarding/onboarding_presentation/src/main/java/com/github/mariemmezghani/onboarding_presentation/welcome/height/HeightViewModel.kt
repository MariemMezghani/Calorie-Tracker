package com.github.mariemmezghani.onboarding_presentation.welcome.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.domain.usecases.FilterOutDigits
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HeightViewModel @Inject constructor(private val preferences: Preferences, private val filterOutDigits: FilterOutDigits) : ViewModel() {

    var height by mutableStateOf<String>("80")
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onHeightEntered(text: String) {
        if (text.length <= 3) {
            height = filterOutDigits(text)

        }
    }

    fun onNextClicked() {

        viewModelScope.launch {
            val height = height.toIntOrNull() ?: kotlin.run {

                _uiEvent.send(UiEvent.ShowSnackbar(UiText.ResourceString(R.string.error_height_cant_be_empty)))

                return@launch

            }

            preferences.saveHeight(height)
            _uiEvent.send(UiEvent.Navigation(Route.WEIGHT))
        }
    }

}