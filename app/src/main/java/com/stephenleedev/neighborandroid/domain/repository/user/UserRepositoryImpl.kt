package com.stephenleedev.neighborandroid.domain.repository.user

import com.stephenleedev.neighborandroid.domain.model.user.thumbnail.PatchUserThumbnailResponse
import com.stephenleedev.neighborandroid.remote.service.user.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun patchUserThumbnail(part: MultipartBody.Part): Flow<PatchUserThumbnailResponse> {
        return flow { emit(userService.patchUserThumbnail(part = part)) }.flowOn(Dispatchers.IO)
    }

}