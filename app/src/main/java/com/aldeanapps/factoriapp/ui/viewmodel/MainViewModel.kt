package com.aldeanapps.factoriapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldeanapps.factoriapp.core.Constants.Companion.MESSAGE_ERROR_FACTORIAL_LONG
import com.aldeanapps.factoriapp.core.Constants.Companion.MESSAGE_ERROR_REQUEST
import com.aldeanapps.factoriapp.core.Constants.Companion.MESSAGE_FIELD_EMPTY
import com.aldeanapps.factoriapp.core.Constants.Companion.MESSAGE_FIELD_RESULT_PREFIX
import com.aldeanapps.factoriapp.core.FactorialUtils.calculateFactorial
import com.aldeanapps.factoriapp.core.FactorialUtils.toExponentialForm
import com.aldeanapps.factoriapp.domain.model.Resource
import com.aldeanapps.factoriapp.domain.model.TimeStampModel
import com.aldeanapps.factoriapp.domain.usecase.GetAllTimeStampsUseCase
import com.aldeanapps.factoriapp.domain.usecase.GetTimeStampUseCase
import com.aldeanapps.factoriapp.domain.usecase.RemoveTimeStampUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTimeStampUseCase: GetTimeStampUseCase,
    private val getAllTimeStampsUseCase: GetAllTimeStampsUseCase,
    private val removeTimeStampUseCase: RemoveTimeStampUseCase
) : ViewModel(){

    private val _timeStamps = MutableLiveData<List<TimeStampModel>?>()
    val timeStamps: LiveData<List<TimeStampModel>?> = _timeStamps

    fun getTimeStamp(action: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            getTimeStampUseCase().collect { result ->
                if (result is Resource.Success) {
                    updateTimeStamps()
                    action("${result.data?.datetime}")
                } else {
                    action(MESSAGE_ERROR_REQUEST)
                }
            }
        }
    }

    fun updateTimeStamps() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTimeStampsUseCase().collect { result ->
                if(result is Resource.Success) {
                    _timeStamps.postValue(result.data)
                }
            }
        }
    }

    fun getFactorial(
        input: String?,
        onLoading: (Boolean) -> Unit,
        action: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            input?.let{
                if (it.isEmpty()) {
                    action(MESSAGE_FIELD_EMPTY)
                } else {
                    calculateFactorial(input).collect{ result ->
                        when(result) {
                            is Resource.Loading -> onLoading(true)
                            is Resource.Error -> {
                                action("$MESSAGE_ERROR_FACTORIAL_LONG")
                                onLoading(false)
                            }
                            is Resource.Success -> {
                                action("$MESSAGE_FIELD_RESULT_PREFIX ${toExponentialForm(result.data!!)}")
                                onLoading(false)
                            }
                        }
                    }
                }
            }
        }
    }

    fun removeTimeStamp(item: TimeStampModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeTimeStampUseCase.invoke(item)
            updateTimeStamps()
        }
    }
}