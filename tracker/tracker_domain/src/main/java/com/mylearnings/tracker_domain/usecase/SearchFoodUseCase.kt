package com.mylearnings.tracker_domain.usecase

import com.mylearnings.core.util.Resource
import com.mylearnings.tracker_domain.model.TrackableFood

interface SearchFoodUseCase {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Resource<List<TrackableFood>>
}