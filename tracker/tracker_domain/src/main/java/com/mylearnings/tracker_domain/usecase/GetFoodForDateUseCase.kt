package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetFoodForDateUseCase {
    operator fun invoke(localDate: LocalDate): Flow<List<TrackedFood>>
}