package com.stephenleedev.neighborandroid.domain.repository.request

import com.stephenleedev.neighborandroid.domain.model.request.RequestModel
import com.stephenleedev.neighborandroid.remote.service.request.RequestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

class RequestRepositoryImpl(private val requestService: RequestService) : RequestRepository {

    override suspend fun getRequestList(latitude: Double, longitude: Double): Flow<List<RequestModel>> {
        return flow {
            emit(requestService.getRequestList(
                latitude = latitude,
                longitude = longitude
            ))
        }.flowOn(Dispatchers.IO)
    }

}