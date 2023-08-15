package com.mylearnings.core.data.model

sealed class Gender(val name: String) {
     object Male : Gender(MALE)
     object Female : Gender(FEMALE)

    companion object {
        fun fromString(name: String): Gender {
            return when (name) {
                MALE -> Male
                else -> Female
            }
        }

        const val MALE = "male"
        const val FEMALE = "female"
    }
}
