package com.aldeanapps.factoriapp.domain.repository

import com.aldeanapps.factoriapp.data.remote.TimeStampDto
import com.aldeanapps.factoriapp.domain.model.TimeStampModel

interface LocalStorageReposotory {
    suspend fun getTimeStamp(): TimeStampModel?
    suspend fun getAllTimeStampsFromLocal(): List<TimeStampModel>
    suspend fun saveTimeStamp(timeStamp: TimeStampDto)
    suspend fun removeTimeStamp(timeStamp: TimeStampModel)
}
