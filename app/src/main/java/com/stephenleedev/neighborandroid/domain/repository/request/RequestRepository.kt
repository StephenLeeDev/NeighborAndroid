package com.stephenleedev.neighborandroid.domain.repository.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

interface RequestRepository {
    suspend fun getRequestList(latitude: Double, longitude: Double): Flow<List<RequestModel>>
}