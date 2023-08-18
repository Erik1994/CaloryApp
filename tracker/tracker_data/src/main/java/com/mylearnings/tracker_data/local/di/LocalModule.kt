package com.mylearnings.tracker_data.local.di

import android.app.Application
import androidx.room.Room
import com.mylearnings.core.coroutines.di.CoroutineModule.DISPATCHER_IO
import com.mylearnings.core.data.manager.ConnectionManager
import com.mylearnings.tracker_data.constants.LocalDbConstants
import com.mylearnings.tracker_data.local.dao.TrackerDao
import com.mylearnings.tracker_data.local.db.TrackerDb
import com.mylearnings.tracker_data.remote.api.OpenFoodApi
import com.mylearnings.tracker_data.repository.TrackerRepositoryImpl
import com.mylearnings.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideTrackerDb(app: Application): TrackerDb {
        return Room.databaseBuilder(
            app,
            TrackerDb::class.java,
            LocalDbConstants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerDao(db: TrackerDb): TrackerDao {
        return db.trackerDao
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        @Named(DISPATCHER_IO) dispatcherIO: CoroutineDispatcher,
        connectionManager: ConnectionManager,
        dao: TrackerDao,
        api: OpenFoodApi
    ): TrackerRepository {
        return TrackerRepositoryImpl(
            dao = dao,
            dispatcherIO = dispatcherIO,
            connectionManager = connectionManager,
            api = api
        )
    }
}