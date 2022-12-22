package com.stephenleedev.neighborandroid.domain.usecase.auth

import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

class GetUserAccessTokenUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): String = authRepository.getUserAccessToken()
}