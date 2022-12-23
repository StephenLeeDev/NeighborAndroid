package com.stephenleedev.neighborandroid.di.usecase.apartment

import com.stephenleedev.neighborandroid.domain.repository.apartment.ApartmentRepository
import com.stephenleedev.neighborandroid.domain.usecase.apartment.GetAllApartmentListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApartmentUseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllApartmentListUseCase(apartmentRepository: ApartmentRepository): GetAllApartmentListUseCase {
        return GetAllApartmentListUseCase(apartmentRepository)
    }

}