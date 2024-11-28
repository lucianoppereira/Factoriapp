package com.aldeanapps.factoriapp.domain.usecase

import com.aldeanapps.factoriapp.data.repository.TimeStampRepositoryImpl
import com.aldeanapps.factoriapp.domain.model.Resource
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTimeStampsUseCase @Inject constructor(
    private val repository: TimeStampRepositoryImpl
) {

    operator fun invoke(): Flow<Resource<List<TimeStampModel>>> = flow {
        try {
            emit(
                Resource.Success(repository.getAllTimeStampsFromLocal())
            )
        } catch (e: Exception) {
            emit(
                Resource.Error(e.message ?: "Unknown Error")
            )
        }
    }
}
