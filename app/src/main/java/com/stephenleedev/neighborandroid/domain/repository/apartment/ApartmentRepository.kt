package com.stephenleedev.neighborandroid.domain.repository.apartment

import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

interface ApartmentRepository {
    suspend fun getAllApartmentList(): Flow<List<ApartmentModel>>
}