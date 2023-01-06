package com.stephenleedev.neighborandroid.domain.usecase.user

import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.PatchUserThumbnailResponse
import com.stephenleedev.neighborandroid.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

class PatchUserThumbnailUseCase(private val userRepository: UserRepository) {
 suspend fun execute(part: MultipartBody.Part): Flow<PatchUserThumbnailResponse> = userRepository.patchUserThumbnail(part = part)
}