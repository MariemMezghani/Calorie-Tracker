package com.github.mariemmezghani.core.domain.model

data class UserInfo(

    val age: Int,
    val gender: Gender,
    val weight: Float,
    val height: Int,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float,
    val activityLevel: ActivityLevel,
    val goalType: GoalType
) {}