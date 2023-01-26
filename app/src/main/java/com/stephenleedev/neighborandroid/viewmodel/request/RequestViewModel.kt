package com.stephenleedev.neighborandroid.viewmodel.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.request.get.RequestState
import com.stephenleedev.neighborandroid.domain.usecase.request.GetRequestListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val getRequestListUseCase: GetRequestListUseCase
) : ViewModel() {

    /**
     * List of all requests
     */
    private val _requestState = MutableLiveData<RequestState>(RequestState.Loading)
    val requestState = _requestState as LiveData<RequestState>

    private fun setRequestState(value: RequestState) {
        _requestState.value = value
    }

    fun getRequestListFromCurrentLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            getRequestListUseCase.execute(
                latitude = latitude,
                longitude = longitude
            )
                .catch {
                    setRequestState(RequestState.Fail)
                }
                .collect { list ->
                    setRequestState(RequestState.Success(list))
                }
        }
    }

    /**
     * Selected request list
     */
    private val _selectedRequestList = MutableLiveData<List<RequestModel>>()
    val selectedRequestList = _selectedRequestList as LiveData<List<RequestModel>>

    fun setSelectedRequestList(value: List<RequestModel>) {
        _selectedRequestList.value = value
    }

}