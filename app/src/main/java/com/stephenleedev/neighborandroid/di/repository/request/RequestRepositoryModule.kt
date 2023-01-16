package com.stephenleedev.neighborandroid.di.repository.request

import com.stephenleedev.neighborandroid.domain.repository.request.RequestRepository
import com.stephenleedev.neighborandroid.domain.repository.request.RequestRepositoryImpl
import com.stephenleedev.neighborandroid.remote.service.request.RequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2023/01/16.
 */

@Module
@InstallIn(SingletonComponent::class)
object RequestRepositoryModule {

    @Provides
    @Singleton
    fun provideRequestRepository(userService: RequestService) : RequestRepository {
        return RequestRepositoryImpl(userService)
    }

}