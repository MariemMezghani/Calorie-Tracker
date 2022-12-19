package com.github.mariemmezghani.tracker_domain.di

import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.tracker_domain.repository.TrackerRepository
import com.github.mariemmezghani.tracker_domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {


    @ViewModelScoped
    @Provides
    fun provideUseCases(repository: TrackerRepository, preferences: Preferences):TrackerUseCases{


        return TrackerUseCases(
            calculateMealNutrients = CalculateMealNutrients(preferences),
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodForDate = GetFoodForDate(repository)
        )

    }
}