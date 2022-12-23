package com.stephenleedev.neighborandroid.domain.usecase.apartment

import com.stephenleedev.neighborandroid.domain.model.apartment.ApartmentModel
import com.stephenleedev.neighborandroid.domain.repository.apartment.ApartmentRepository
import kotlinx.coroutines.flow.Flow

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

class GetAllApartmentListUseCase(private val apartmentRepository: ApartmentRepository) {
    suspend fun execute(): Flow<List<ApartmentModel>> = apartmentRepository.getAllApartmentList()
}