package com.stephenleedev.neighborandroid.domain.usecase.auth

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthRequest
import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

class GetIsSocialAccountExistUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(body: SocialAuthRequest): Flow<ResponseBody> = authRepository.getIsSocialAccountExist(body = body)
}