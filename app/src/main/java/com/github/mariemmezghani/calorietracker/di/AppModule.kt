package com.github.mariemmezghani.calorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.github.mariemmezghani.core.domain.DefaultPreferences
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.domain.usecases.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application):SharedPreferences{
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences):Preferences{
        return DefaultPreferences(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideFilterOutDigits():FilterOutDigits{
        return FilterOutDigits()
    }

}