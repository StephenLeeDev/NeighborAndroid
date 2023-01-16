package com.stephenleedev.neighborandroid.domain.usecase.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.repository.request.RequestRepository
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

class GetRequestListUseCase(private val requestRepository: RequestRepository) {

    suspend fun execute(latitude: Double, longitude: Double): Flow<List<RequestModel>> =
        requestRepository.getRequestList(
            latitude = latitude,
            longitude = longitude
        )

}