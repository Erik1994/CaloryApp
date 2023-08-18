package com.mylearnings.tracker_data.mapper

import com.mylearnings.core.data.mapper.Mapper
import com.mylearnings.tracker_data.local.entity.TrackedFoodEntity
import com.mylearnings.tracker_domain.model.MealType
import com.mylearnings.tracker_domain.model.TrackedFood
import java.time.LocalDate

val TRACKED_FOOD_ENTITY_TO_TRACKED_FOOD: Mapper<TrackedFoodEntity, TrackedFood> = Mapper { s ->
    s.run {
        TrackedFood(
            name = name,
            carbs = carbs,
            protein = protein,
            fat = fat,
            imageUrl = imageUrl,
            type = MealType.fromString(type),
            amount = amount,
            date = LocalDate.of(year, month, dayOfMonth),
            calories = calories,
            id = id
        )
    }
}

val TRACKED_FOOD_TO_TRACKED_FOOD_ENTITY: Mapper<TrackedFood, TrackedFoodEntity> = Mapper { s ->
    s.run {
        TrackedFoodEntity(
            name = name,
            carbs = carbs,
            protein = protein,
            fat = fat,
            imageUrl = imageUrl,
            type = type.name,
            amount = amount,
            calories = calories,
            dayOfMonth = date.dayOfMonth,
            year = date.year,
            month = date.monthValue,
            id = id
        )
    }
}