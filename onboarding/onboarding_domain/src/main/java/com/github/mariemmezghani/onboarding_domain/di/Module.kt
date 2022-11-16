package com.github.mariemmezghani.onboarding_domain.di

import com.github.mariemmezghani.onboarding_domain.use_case.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object Module {

    @Provides
    @ViewModelScoped
    fun validateNutrients(
        carbRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): ValidateNutrients {
        return ValidateNutrients()
    }
}