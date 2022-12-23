package com.stephenleedev.neighborandroid.domain.repository.apartment

import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import com.stephenleedev.neighborandroid.remote.service.apartment.ApartmentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

class ApartmentRepositoryImpl(private val apartmentService: ApartmentService) : ApartmentRepository {

    override suspend fun getAllApartmentList(): Flow<List<ApartmentModel>> {
        return flow { emit(apartmentService.getAllApartmentList()) }.flowOn(Dispatchers.IO)
    }

}