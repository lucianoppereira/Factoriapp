package com.aldeanapps.factoriapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TimeStampDao {

    @Query("SELECT * FROM time_stamps")
    suspend fun getAllTimeStamps(): MutableList<TimeStampEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeStamp(timeStamp: TimeStampEntity)

    @Delete
    suspend fun deleteTimeStamp(timeStamp: TimeStampEntity)
}
