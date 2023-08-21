package com.mylearnings.onboarding_domain.di

import com.mylearnings.onboarding_domain.usecase.ValidateNutrientsUseCase
import com.mylearnings.onboarding_domain.usecase.ValidateNutrientsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {
    @ViewModelScoped
    @Binds
    abstract fun bindValidateNutrientsUseCase(validateNutrients: ValidateNutrientsUseCaseImpl): ValidateNutrientsUseCase
}