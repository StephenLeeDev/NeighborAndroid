package com.stephenleedev.neighborandroid.viewmodel.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplyState
import com.stephenleedev.neighborandroid.domain.usecase.request.PostApplyToRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

@HiltViewModel
class RequestApplyViewModel @Inject constructor(
    private val postApplyToRequestUseCase: PostApplyToRequestUseCase
) : ViewModel() {

    /**
     * Request applying status
     */
    private val _requestApplyState = MutableLiveData<RequestApplyState>(RequestApplyState.Ready)
    val requestApplyState = _requestApplyState as LiveData<RequestApplyState>

    private fun setRequestApplyState(value: RequestApplyState) {
        _requestApplyState.value = value
    }

    fun postApplyToRequest(id: Int) {
        viewModelScope.launch {

            setRequestApplyState(RequestApplyState.Loading)

            postApplyToRequestUseCase.execute(id = id)
                .catch {
                    setRequestApplyState(RequestApplyState.Fail)
                }
                .collect { response ->
                    setRequestApplyState(RequestApplyState.Success(requestApplyResponse = response))
                }
        }
    }

}