package com.github.mariemmezghani.onboarding_presentation.welcome.nutrient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.domain.usecases.FilterOutDigits
import com.github.mariemmezghani.core.navigation.Route
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutrientViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateNutrients: ValidateNutrients,
    private val filterOutDigits: FilterOutDigits,
) : ViewModel() {


    var state by mutableStateOf<NutrientState>(NutrientState("40","30","30"))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(nutrientEvent: NutrientEvent) {

        when (nutrientEvent) {

            is NutrientEvent.OnCarbEntered -> {
                state = state.copy(carbRatio = filterOutDigits(nutrientEvent.carbRatio))
            }
            is NutrientEvent.OnProteinEntered -> {
                state = state.copy(proteinRatio = filterOutDigits(nutrientEvent.proteinRatio))
            }

            is NutrientEvent.OnFatEntered -> {
                state = state.copy(fatRatio = filterOutDigits(nutrientEvent.fatRatio))
            }

            is NutrientEvent.OnNextClicked -> {

                val result = validateNutrients(state.carbRatio, state.proteinRatio, state.fatRatio)


                when (result) {
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.errorMessage))

                        }


                    }
                    is ValidateNutrients.Result.Success -> {

                        viewModelScope.launch {
                            preferences.carbRatio(result.carbRatio)
                            preferences.proteinRatio(result.proteinRatio)
                            preferences.fatRatio(result.fatRatio)
                            _uiEvent.send(UiEvent.Navigation(Route.TRACKER))
                        }
                    }
                }
            }
        }
    }
}