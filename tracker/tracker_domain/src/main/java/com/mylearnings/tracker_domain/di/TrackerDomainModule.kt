package com.mylearnings.tracker_domain.di

import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.tracker_domain.repository.TrackerRepository
import com.mylearnings.tracker_domain.usecase.CalculateMealNutrientsUseCase
import com.mylearnings.tracker_domain.usecase.CalculateMealNutrientsUseCaseImpl
import com.mylearnings.tracker_domain.usecase.DeleteFoodUseCase
import com.mylearnings.tracker_domain.usecase.DeleteFoodUseCaseImpl
import com.mylearnings.tracker_domain.usecase.GetFoodForDateUseCase
import com.mylearnings.tracker_domain.usecase.GetFoodForDateUseCaseImpl
import com.mylearnings.tracker_domain.usecase.SearchFoodUseCase
import com.mylearnings.tracker_domain.usecase.SearchFoodUseCaseImpl
import com.mylearnings.tracker_domain.usecase.TrackFoodUseCase
import com.mylearnings.tracker_domain.usecase.TrackFoodUseCaseImpl
import com.mylearnings.tracker_domain.usecase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {
    @ViewModelScoped
    @Provides
    fun provideSearchFoodUseCase(trackerRepository: TrackerRepository): SearchFoodUseCase =
        SearchFoodUseCaseImpl(trackerRepository)

    @ViewModelScoped
    @Provides
    fun provideGetFoodForDateUseCase(trackerRepository: TrackerRepository): GetFoodForDateUseCase =
        GetFoodForDateUseCaseImpl(trackerRepository)

    @ViewModelScoped
    @Provides
    fun provideDeleteFoodUseCase(trackerRepository: TrackerRepository): DeleteFoodUseCase =
        DeleteFoodUseCaseImpl(trackerRepository)

    @ViewModelScoped
    @Provides
    fun provideTCalculateNutrientsUseCase(preferences: Preferences): CalculateMealNutrientsUseCase =
        CalculateMealNutrientsUseCaseImpl(preferences)

    @ViewModelScoped
    @Provides
    fun provideTrackFoodUseCase(trackerRepository: TrackerRepository): TrackFoodUseCase =
        TrackFoodUseCaseImpl(trackerRepository)

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        trackFoodUseCase: TrackFoodUseCase,
        deleteFoodUseCase: DeleteFoodUseCase,
        searchFoodUseCase: SearchFoodUseCase,
        getFoodForDateUseCase: GetFoodForDateUseCase,
        calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase
    ): TrackerUseCases = TrackerUseCases(
        trackFoodUseCase = trackFoodUseCase,
        searchFoodUseCase = searchFoodUseCase,
        deleteFoodUseCase = deleteFoodUseCase,
        getFoodForDateUseCase = getFoodForDateUseCase,
        calculateMealNutrientsUseCase = calculateMealNutrientsUseCase
    )
}