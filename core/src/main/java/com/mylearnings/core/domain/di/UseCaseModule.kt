package com.mylearnings.core.domain.di

import com.mylearnings.core.domain.usecase.FilterOutDigits.FilterOutDigitsUseCase
import com.mylearnings.core.domain.usecase.FilterOutDigits.FilterOutDigitsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindFilterOutDigitUseCase(filterOutDigitsUseCase: FilterOutDigitsUseCaseImpl): FilterOutDigitsUseCase
}