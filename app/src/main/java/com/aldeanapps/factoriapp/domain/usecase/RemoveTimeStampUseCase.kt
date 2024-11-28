package com.aldeanapps.factoriapp.domain.usecase

import com.aldeanapps.factoriapp.data.repository.TimeStampRepositoryImpl
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import javax.inject.Inject

class RemoveTimeStampUseCase @Inject constructor(
    private val repository: TimeStampRepositoryImpl
){

    suspend operator fun invoke(timeStamp: TimeStampModel) {
        repository.removeTimeStamp(timeStamp)
    }
}
