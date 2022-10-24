package com.github.mariemmezghani.core.domain.preferences

import com.github.mariemmezghani.core.domain.model.ActivityLevel
import com.github.mariemmezghani.core.domain.model.Gender
import com.github.mariemmezghani.core.domain.model.GoalType
import com.github.mariemmezghani.core.domain.model.UserInfo

interface Preferences {

    fun saveGender(gender:Gender)
    fun saveAge(age: Int)
    fun saveWeight(weight:Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(activityLevel: ActivityLevel)
    fun proteinRatio(ratio: Float)
    fun carbRatio(ratio: Float)
    fun fatRatio(ratio: Float)
    fun saveGoalType(goalType: GoalType)
    fun loadUserInfo(): UserInfo

    companion object{
        const val AGE_KEY = "age"
        const val GENDER_KEY = "gender"
        const val HEIGHT_KEY = "height"
        const val WEIGHT_KEY = "weight"
        const val ACTIVITYLEVEL_KEY = "activity_level"
        const val PROTEINRATIO_KEY = "protein_ratio"
        const val CARBRATIO_KEY = "carb_ratio"
        const val FATRATIO_KEY = "fat_ratio"
        const val GOALTYPE_KEY = "goal_type"
    }
}