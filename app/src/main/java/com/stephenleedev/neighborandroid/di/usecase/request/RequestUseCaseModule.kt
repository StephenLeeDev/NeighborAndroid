package com.stephenleedev.neighborandroid.di.usecase.request

import com.stephenleedev.neighborandroid.domain.repository.request.RequestRepository
import com.stephenleedev.neighborandroid.domain.usecase.request.GetRequestListUseCase
import com.stephenleedev.neighborandroid.domain.usecase.request.PostApplyToRequestUseCase
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
object RequestUseCaseModule {

    @Provides
    @Singleton
    fun provideGetRequestListUseCase(requestRepository: RequestRepository): GetRequestListUseCase {
        return GetRequestListUseCase(requestRepository)
    }

    @Provides
    @Singleton
    fun providePostApplyToRequestUseCase(requestRepository: RequestRepository): PostApplyToRequestUseCase {
        return PostApplyToRequestUseCase(requestRepository)
    }

}