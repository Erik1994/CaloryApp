package com.mylearnings.core.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mylearnings.core.data.preferences.DefaultPreferences
import com.mylearnings.core.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SHARED_PREF_NAME = "com.mylearnings.caloryapp.shared_pref"

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ) : SharedPreferences {
        return app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return DefaultPreferences(sharedPreferences)
    }
}