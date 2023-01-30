package com.stephenleedev.neighborandroid.domain.repository.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.domain.model.request.post.RequestApplyResponse
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

interface RequestRepository {
    suspend fun getRequestList(latitude: Double, longitude: Double): Flow<List<RequestModel>>
    suspend fun postApplyToRequest(id: Int): Flow<RequestApplyResponse>
}