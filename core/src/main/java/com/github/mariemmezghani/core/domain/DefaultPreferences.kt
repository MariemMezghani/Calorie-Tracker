package com.github.mariemmezghani.core.domain

import android.content.SharedPreferences
import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.domain.model.GoalType
import com.github.mariemmezghani.core.domain.model.UserInfo
import com.github.mariemmezghani.core.domain.preferences.Preferences
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.ACTIVITYLEVEL_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.AGE_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.CARBRATIO_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.FATRATIO_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.GENDER_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.GOALTYPE_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.HEIGHT_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.PROTEINRATIO_KEY
import com.github.mariemmezghani.core.domain.preferences.Preferences.Companion.WEIGHT_KEY

class DefaultPreferences(val sharedPref: SharedPreferences) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit().putString(GENDER_KEY, gender.name).apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit().putInt(AGE_KEY, age).apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit().putFloat(WEIGHT_KEY, weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit().putInt(HEIGHT_KEY, height).apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPref.edit().putString(ACTIVITYLEVEL_KEY, activityLevel.name).apply()
    }

    override fun proteinRatio(ratio: Float) {
        sharedPref.edit().putFloat(PROTEINRATIO_KEY, ratio).apply()
    }

    override fun carbRatio(ratio: Float) {
        sharedPref.edit().putFloat(CARBRATIO_KEY, ratio).apply()
    }

    override fun fatRatio(ratio: Float) {
        sharedPref.edit().putFloat(FATRATIO_KEY, ratio).apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit().putString(GOALTYPE_KEY, goalType.name).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(AGE_KEY, -1)
        val gender = sharedPref.getString(GENDER_KEY, null)
        val weight = sharedPref.getFloat(WEIGHT_KEY, 1f)
        val height = sharedPref.getInt(HEIGHT_KEY, 1)
        val carbRatio = sharedPref.getFloat(CARBRATIO_KEY, 1f)
        val proteinRatio = sharedPref.getFloat(PROTEINRATIO_KEY, 1f)
        val faRatio = sharedPref.getFloat(FATRATIO_KEY, 1f)
        val activityLevel = sharedPref.getString(ACTIVITYLEVEL_KEY, null)
        val goalType = sharedPref.getString(GOALTYPE_KEY, null)

        return UserInfo(
            age = age,
            gender = Gender.fromString(gender ?: "male"),
            weight = weight,
            height = height,
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = faRatio,
            activityLevel = ActivityLevel.fromString(activityLevel ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keepWeight")
        )

    }

}