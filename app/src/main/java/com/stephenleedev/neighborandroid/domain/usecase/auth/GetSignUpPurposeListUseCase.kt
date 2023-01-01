package com.stephenleedev.neighborandroid.domain.usecase.auth

import com.stephenleedev.neighborandroid.domain.model.auth.register.SignUpPurposeModel
import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

class GetSignUpPurposeListUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(): Flow<List<SignUpPurposeModel>> = authRepository.getSignUpPurposeList()
}