package com.github.mariemmezghani.onboarding_presentation.welcome.nutrient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.util.UiEvent
import com.github.mariemmezghani.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutrientViewModel @Inject constructor(private val preferences: Preferences, private val validateNutrients: ValidateNutrients):ViewModel(){


    var state by mutableStateOf<NutrientState>(NutrientState())
        private set

    private val _uiEvent= Channel<UiEvent>()
    val uiEvent= _uiEvent.receiveAsFlow()

    fun onEvent(nutrientEvent: NutrientEvent){

        when(nutrientEvent){

            is NutrientEvent.OnCarbEntered -> {
                state= state.copy(carbRatio = nutrientEvent.carbRatio)
            }
            is NutrientEvent.OnProteinEntered -> {
                state= state.copy(proteinRatio = nutrientEvent.proteinRatio)
            }

            is NutrientEvent.OnFatEntered -> {
                state = state.copy(fatRatio = nutrientEvent.fatRatio)
            }
            is NutrientEvent.OnNextClicked-> {

                    val result = validateNutrients(state.carbRatio, state.proteinRatio, state.fatRatio)


                when (result){
                    is ValidateNutrients.Result.Error -> {}
                    is ValidateNutrients.Result.Success -> {}
                }
            }
        }
    }
}