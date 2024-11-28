package com.aldeanapps.factoriapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aldeanapps.factoriapp.domain.model.TimeStampModel

@Entity(tableName = "time_stamps")
data class TimeStampEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("currentDateTime")
    val dateTime: String,
    @ColumnInfo("utcOffset")
    val utcOffset: String,
    @ColumnInfo("dayOfTheWeek")
    val dayOfWeek: String,
    @ColumnInfo("timeZoneName")
    val timeZone: String
)

fun TimeStampEntity.toTimeStampModel() = TimeStampModel(
    id = id,
    datetime = dateTime,
    utcOffset = utcOffset,
    dayOfWeek = dayOfWeek,
    timeZone = timeZone
)
