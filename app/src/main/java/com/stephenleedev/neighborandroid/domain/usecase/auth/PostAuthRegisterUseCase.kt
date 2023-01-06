package com.stephenleedev.neighborandroid.domain.usecase.auth

import com.stephenleedev.neighborandroid.domain.model.auth.SocialAuthResponse
import com.stephenleedev.neighborandroid.domain.model.auth.register.RegisterRequest
import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

class PostAuthRegisterUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(body: RegisterRequest): Flow<SocialAuthResponse> = authRepository.postAuthRegister(body = body)
}