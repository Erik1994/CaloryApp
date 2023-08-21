package com.mylearnings.core.domain.di

import com.mylearnings.core.domain.usecase.FilterOutDigitsUseCase
import com.mylearnings.core.domain.usecase.FilterOutDigitsUseCaseImpl
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
    abstract fun bindFilterOutDigitUseCase(filterOutDigitsUseCase: FilterOutDigitsUseCaseImpl): FilterOutDigitsUseCase
}