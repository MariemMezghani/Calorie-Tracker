package com.github.mariemmezghani.onboarding_domain.use_case

import com.github.mariemmezghani.core.R
import com.github.mariemmezghani.core.util.UiText


class ValidateNutrients {


    operator fun invoke(
        carbRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Result {
        val carbRatio = carbRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()


        if (carbRatio == null || proteinRatio == null || fatRatio == null) {

            return Result.Error(UiText.ResourceString(R.string.error_invalid_values))

        }

        if ((carbRatio + proteinRatio + fatRatio) != 100) {

            return Result.Error(UiText.ResourceString(R.string.error_not_100_percent))
        }

        return Result.Success(carbRatio / 100f, proteinRatio / 100f, fatRatio / 100f)

    }


    sealed class Result {

        data class Error(val errorMessage: UiText) : Result()

        data class Success(val carbRatio: Float, val proteinRatio: Float, val fatRatio: Float) :
            Result()
    }
}