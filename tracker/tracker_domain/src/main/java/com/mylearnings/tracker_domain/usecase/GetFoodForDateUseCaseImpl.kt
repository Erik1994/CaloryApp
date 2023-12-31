package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodForDateUseCaseImpl(
    private val repository: TrackerRepository
) : GetFoodForDateUseCase {
    override operator fun invoke(localDate: LocalDate): Flow<List<TrackedFood>> =
        repository.getFoodsForDate(localDate)
}