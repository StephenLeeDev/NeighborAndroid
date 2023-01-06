package com.stephenleedev.neighborandroid.domain.repository.user

import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.PatchUserThumbnailResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

interface UserRepository {
    suspend fun patchUserThumbnail(part: MultipartBody.Part): Flow<PatchUserThumbnailResponse>
}