package com.stephenleedev.neighborandroid.domain.model.user.thumbnail

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

sealed class UserThumbnailUpdateState {

    object Loading : UserThumbnailUpdateState()
    object Fail : UserThumbnailUpdateState()
    data class Success(val newThumbnailUri: String) : UserThumbnailUpdateState()
}
