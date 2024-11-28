package com.aldeanapps.factoriapp.data.remote

import com.aldeanapps.factoriapp.core.DayOfWeek
import com.aldeanapps.factoriapp.data.local.TimeStampEntity
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.google.gson.annotations.SerializedName

data class TimeStampDto(
    @SerializedName("datetime") val dateTime: String,
    @SerializedName("utc_offset") val utcOffset: String,
    @SerializedName("day_of_week") val dayOfWeek: Int,
    @SerializedName("timezone") val timeZone: String,
)

fun TimeStampDto.toTimeStampEntity() = TimeStampEntity(
    dateTime = dateTime,
    utcOffset = utcOffset,
    dayOfWeek = DayOfWeek.entries[dayOfWeek].day,
    timeZone = timeZone
)

fun TimeStampDto.toTimeStampModel() = TimeStampModel(
    datetime = dateTime,
    utcOffset = utcOffset,
    dayOfWeek = DayOfWeek.entries[dayOfWeek].day,
    timeZone = timeZone
)
