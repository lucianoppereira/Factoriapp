package com.aldeanapps.factoriapp.domain.usecase

import com.aldeanapps.factoriapp.data.repository.TimeStampRepositoryImpl
import com.aldeanapps.factoriapp.domain.model.Resource
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTimeStampUseCase @Inject constructor(
    private val repository: TimeStampRepositoryImpl
) {

    operator fun invoke(): Flow<Resource<TimeStampModel>> = flow {
        try {
            emit(
                Resource.Success(repository.getTimeStamp())
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(e.message ?: "Unknown Error")
            )
        }
    }
}
