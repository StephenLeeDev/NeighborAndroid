package com.stephenleedev.neighborandroid.domain.model.request.get

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

sealed class RequestState  {
    object Loading : RequestState()
    object Fail : RequestState()
    data class Success(val list: List<RequestModel>) : RequestState()
}