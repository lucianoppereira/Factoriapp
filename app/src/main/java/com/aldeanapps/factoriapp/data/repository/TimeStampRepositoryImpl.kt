package com.aldeanapps.factoriapp.data.repository

import com.aldeanapps.factoriapp.data.local.TimeStampDao
import com.aldeanapps.factoriapp.data.local.toTimeStampModel
import com.aldeanapps.factoriapp.data.remote.TimeStampApi
import com.aldeanapps.factoriapp.data.remote.TimeStampDto
import com.aldeanapps.factoriapp.data.remote.toTimeStampEntity
import com.aldeanapps.factoriapp.data.remote.toTimeStampModel
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.aldeanapps.factoriapp.domain.model.toTimeStampEntity
import com.aldeanapps.factoriapp.domain.repository.LocalStorageReposotory
import javax.inject.Inject

class TimeStampRepositoryImpl @Inject constructor(
    private val api: TimeStampApi,
    private val timeStampDao: TimeStampDao
) : LocalStorageReposotory {

    override suspend fun getTimeStamp(): TimeStampModel? {
        return api.getTimeStamp()?.let {
            saveTimeStamp(it)
            return it.toTimeStampModel()
        }
    }

    override suspend fun getAllTimeStampsFromLocal(): List<TimeStampModel> =
        timeStampDao.getAllTimeStamps().map { it.toTimeStampModel() }

    override suspend fun saveTimeStamp(timeStamp: TimeStampDto) {
        timeStampDao.insertTimeStamp(timeStamp.toTimeStampEntity())
    }

    override suspend fun removeTimeStamp(timeStamp: TimeStampModel) {
        timeStampDao.deleteTimeStamp(timeStamp.toTimeStampEntity())
    }
}
