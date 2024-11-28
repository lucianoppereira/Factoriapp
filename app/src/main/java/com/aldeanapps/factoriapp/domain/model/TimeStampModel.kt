package com.aldeanapps.factoriapp.domain.model

import com.aldeanapps.factoriapp.data.local.TimeStampEntity

data class TimeStampModel(
    val id: Int? = null,
    val datetime: String,
    val utcOffset: String,
    val dayOfWeek: String,
    val timeZone: String
)

fun TimeStampModel.toTimeStampEntity() = TimeStampEntity(
    id = id ?: 0,
    dateTime = datetime,
    utcOffset = utcOffset,
    dayOfWeek = dayOfWeek,
    timeZone = timeZone,
)
