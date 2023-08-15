package com.mylearnings.core.data.preferences

import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.data.model.UserInfo

interface Preferences {
    fun saveAge(age: Int)
    fun saveWight(weight: Float)
    fun saveHeight(height: Int)
    fun saveFatRatio(ratio: Float)
    fun saveCarbsRatio(ratio: GoalType)
    fun saveProteinRatio(ratio: Float)
    fun saveGender(gender: Gender)
    fun saveGaolType(type: GoalType)
    fun saveActivityLevel(level: ActivityLevel)

    fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}