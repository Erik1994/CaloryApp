package com.mylearnings.core.data.preferences

import android.content.SharedPreferences
import com.mylearnings.core.data.extensions.get
import com.mylearnings.core.data.extensions.put
import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.data.model.UserInfo

internal class DefaultPreferences(private val sharedPreferences: SharedPreferences) : Preferences {
    override fun saveAge(age: Int) {
        sharedPreferences.put(Preferences.KEY_AGE, age)
    }

    override fun saveWight(weight: Float) {
        sharedPreferences.put(Preferences.KEY_WEIGHT, weight)
    }

    override fun saveHeight(height: Int) {
        sharedPreferences.put(Preferences.KEY_HEIGHT, height)
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPreferences.put(Preferences.KEY_FAT_RATIO, ratio)
    }

    override fun saveCarbsRatio(ratio: GoalType) {
        sharedPreferences.put(Preferences.KEY_CARB_RATIO, ratio)
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPreferences.put(Preferences.KEY_PROTEIN_RATIO, ratio)
    }

    override fun saveGender(gender: Gender) {
        sharedPreferences.put(Preferences.KEY_GENDER, gender.name)
    }

    override fun saveGaolType(type: GoalType) {
        sharedPreferences.put(Preferences.KEY_GOAL_TYPE, type.name)
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPreferences.put(Preferences.KEY_ACTIVITY_LEVEL, level.name)
    }

    override fun loadUserInfo(): UserInfo {
        val age: Int = sharedPreferences.get(Preferences.KEY_AGE, 0)
        val height: Int = sharedPreferences.get(Preferences.KEY_HEIGHT, 0)
        val weight: Float = sharedPreferences.get(Preferences.KEY_WEIGHT, 0f)
        val fatRatio: Float = sharedPreferences.get(Preferences.KEY_FAT_RATIO, 0f)
        val carbRatio: Float = sharedPreferences.get(Preferences.KEY_CARB_RATIO, 0f)
        val proteinRatio: Float = sharedPreferences.get(Preferences.KEY_PROTEIN_RATIO, 0f)
        val gender: Gender = Gender.fromString(sharedPreferences.get(Preferences.KEY_GENDER, Gender.MALE))
        val goalType: GoalType =
            GoalType.fromString(sharedPreferences.get(Preferences.KEY_GOAL_TYPE, GoalType.KEEP_WEIGHT))
        val activityLevel: ActivityLevel =
            ActivityLevel.fromString(sharedPreferences.get(Preferences.KEY_ACTIVITY_LEVEL, ActivityLevel.MEDIUM))
        return UserInfo(
            age = age,
            height =height,
            gender = gender,
            weight = weight,
            fatRatio = fatRatio,
            goalType = goalType,
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            activityLevel = activityLevel
        )
    }
}