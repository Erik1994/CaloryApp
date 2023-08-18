package com.mylearnings.tracker_data.mapper

import com.mylearnings.core.data.mapper.Mapper
import com.mylearnings.core.util.orDefault
import com.mylearnings.tracker_data.remote.dto.Product
import com.mylearnings.tracker_domain.model.TrackableFood

private const val DEFAULT_PER_100G_VALUE = 0.0

val PRODUCT_TO_TRACKABLE_FOOD: Mapper<Product, TrackableFood> = Mapper { product ->
    product.run {
        TrackableFood(
            name = productName.orEmpty(),
            imageUrl = imageFrontThumbUrl,
            caloriesPer100g = nutriments.energyKcal100g.orDefault(DEFAULT_PER_100G_VALUE).toInt(),
            carbsPer100g = nutriments.carbohydrates100g.orDefault(DEFAULT_PER_100G_VALUE).toInt(),
            proteinPer100g = nutriments.proteins100g.orDefault(DEFAULT_PER_100G_VALUE).toInt(),
            fatPer100g = nutriments.fat100g.orDefault(DEFAULT_PER_100G_VALUE).toInt()
        )
    }
}

val PRODUCTS_TO_TRACKABLE_FOODS: Mapper<List<Product?>, List<TrackableFood>> = Mapper { products ->
    products.filterNotNull().map {
        PRODUCT_TO_TRACKABLE_FOOD.map(it)
    }
}