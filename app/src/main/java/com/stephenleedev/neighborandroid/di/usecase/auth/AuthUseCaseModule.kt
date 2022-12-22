package com.stephenleedev.neighborandroid.di.usecase.auth

import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetIsSocialAuthExistUseCase
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetUserAccessTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2022/12/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule {

    @Provides
    @Singleton
    fun provideGetUserAccessTokenUseCase(authRepository: AuthRepository): GetUserAccessTokenUseCase {
        return GetUserAccessTokenUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetIsSocialAuthExistUseCase(authRepository: AuthRepository): GetIsSocialAuthExistUseCase {
        return GetIsSocialAuthExistUseCase(authRepository)
    }

}