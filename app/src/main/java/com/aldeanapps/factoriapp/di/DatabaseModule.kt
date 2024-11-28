package com.aldeanapps.factoriapp.di

import android.content.Context
import androidx.room.Room
import com.aldeanapps.factoriapp.data.local.TimeStampDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext appContext: Context): TimeStampDatabase {
        return Room.databaseBuilder(
            appContext,
            TimeStampDatabase::class.java,
            "stamp_history_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocationDao(db: TimeStampDatabase) = db.timeStampDao()
}
