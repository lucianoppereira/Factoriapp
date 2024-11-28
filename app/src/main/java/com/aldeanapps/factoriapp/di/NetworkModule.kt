package com.aldeanapps.factoriapp.di

import com.aldeanapps.factoriapp.BuildConfig
import com.aldeanapps.factoriapp.data.remote.TimeStampClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesLocationClient(retrofit: Retrofit) =
        retrofit.create(TimeStampClient::class.java)
}
