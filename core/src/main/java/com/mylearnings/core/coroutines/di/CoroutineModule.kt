package com.mylearnings.core.coroutines.di

import com.mylearnings.core.coroutines.AppDispatchers
import com.mylearnings.core.coroutines.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatchersImpl
    }

    @Provides
    @Singleton
    @Named(DISPATCHER_IO)
    fun provideDispatcherIO(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.ioDispatcher
    }

    @Provides
    @Singleton
    @Named(DISPATCHER_UI)
    fun provideDispatcherUI(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.mainDispatcher
    }

    @Provides
    @Singleton
    @Named(DISPATCHER_DEFAULT)
    fun provideDispatcherDefault(appDispatchers: AppDispatchers): CoroutineDispatcher {
        return appDispatchers.defaultDispatcher
    }

    const val DISPATCHER_IO = "DISPATCHER_IO"
    const val DISPATCHER_UI = "DISPATCHER_UI"
    const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"
}