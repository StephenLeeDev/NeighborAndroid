package com.stephenleedev.neighborandroid.domain.model.request.post

/**
 * Written by StephenLeeDev on 2023/01/30.
 */

sealed class RequestApplyState {
    object Ready : RequestApplyState()
    object Loading : RequestApplyState()
    object Fail : RequestApplyState()
    data class Success(val requestApplicationModel: RequestApplicationModel) : RequestApplyState()
}
