package com.stephenleedev.neighborandroid.di.repository.apartment

import com.stephenleedev.neighborandroid.domain.repository.apartment.ApartmentRepository
import com.stephenleedev.neighborandroid.domain.repository.apartment.ApartmentRepositoryImpl
import com.stephenleedev.neighborandroid.remote.service.apartment.ApartmentService
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
object ApartmentRepositoryModule {

    @Provides
    @Singleton
    fun provideApartmentRepository(
        authService: ApartmentService
    ) : ApartmentRepository {
        return ApartmentRepositoryImpl(authService)
    }

}