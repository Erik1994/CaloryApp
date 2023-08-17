package com.mylearnings.tracker_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mylearnings.tracker_data.local.dao.TrackerDao
import com.mylearnings.tracker_data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDb: RoomDatabase() {
    abstract val trackerDao: TrackerDao
}