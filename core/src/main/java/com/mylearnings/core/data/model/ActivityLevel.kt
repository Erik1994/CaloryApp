package com.mylearnings.core.data.model

sealed class ActivityLevel(val name: String) {
     object Low : ActivityLevel(LOW)
     object Medium : ActivityLevel(MEDIUM)
     object High : ActivityLevel(HIGH)

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when (name) {
                LOW -> Low
                MEDIUM -> Medium
                else -> High
            }
        }

        const val LOW = "low"
        const val MEDIUM = "medium"
        const val HIGH = "high"
    }
}
