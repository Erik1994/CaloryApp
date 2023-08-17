package com.mylearnings.tracker_data.local.di

import android.app.Application
import androidx.room.Room
import com.mylearnings.tracker_data.constants.LocalDbConstants
import com.mylearnings.tracker_data.local.dao.TrackerDao
import com.mylearnings.tracker_data.local.db.TrackerDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}