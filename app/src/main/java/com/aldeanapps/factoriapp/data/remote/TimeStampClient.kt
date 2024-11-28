package com.aldeanapps.factoriapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface TimeStampClient {
    @GET("/api/timezone/America/Buenos_Aires")
    suspend fun getTimeStamp(): Response<TimeStampDto>
}
