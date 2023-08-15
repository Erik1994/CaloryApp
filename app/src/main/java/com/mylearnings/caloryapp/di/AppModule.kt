package com.mylearnings.caloryapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val SHARED_PREF_NAME = "com.mylearnings.caloryapp.shared_pref"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

}