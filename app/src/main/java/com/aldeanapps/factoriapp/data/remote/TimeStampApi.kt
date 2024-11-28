package com.aldeanapps.factoriapp.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TimeStampApi @Inject constructor(private val api: TimeStampClient) {

    suspend fun getTimeStamp(): TimeStampDto? {
        return withContext(Dispatchers.IO) {
            val response = api.getTimeStamp()
            response.body()
        }
    }
}
