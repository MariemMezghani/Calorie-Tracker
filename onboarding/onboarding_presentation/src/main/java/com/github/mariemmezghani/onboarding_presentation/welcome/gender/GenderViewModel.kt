package com.github.mariemmezghani.onboarding_presentation.welcome.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.core.domain.DefaultPreferences
import com.github.mariemmezghani.core.domain.model.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GenderViewModel  @Inject constructor (private val preferences: DefaultPreferences):ViewModel() {


    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun selectGender(gender: Gender){

        selectedGender = gender
    }



}