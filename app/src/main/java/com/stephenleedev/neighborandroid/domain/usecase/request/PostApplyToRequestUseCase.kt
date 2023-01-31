package com.stephenleedev.neighborandroid.domain.usecase.request

import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplicationModel
import com.stephenleedev.neighborandroid.domain.repository.request.RequestRepository
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2023/01/30.
 */

class PostApplyToRequestUseCase(private val requestRepository: RequestRepository) {

    suspend fun execute(id: Int): Flow<RequestApplicationModel> =
        requestRepository.postApplyToRequest(id = id)

}