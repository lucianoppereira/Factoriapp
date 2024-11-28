package com.aldeanapps.factoriapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeStampEntity::class], version = 1)
abstract class TimeStampDatabase : RoomDatabase() {
    abstract fun timeStampDao(): TimeStampDao
}
