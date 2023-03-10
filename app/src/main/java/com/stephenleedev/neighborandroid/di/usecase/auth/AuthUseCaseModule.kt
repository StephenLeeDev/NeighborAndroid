package com.stephenleedev.neighborandroid.di.usecase.auth

import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetIsSocialAccountExistUseCase
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetSignUpPurposeListUseCase
import com.stephenleedev.neighborandroid.domain.usecase.auth.GetUserAccessTokenUseCase
import com.stephenleedev.neighborandroid.domain.usecase.auth.PostAuthRegisterUseCase
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
    fun provideGetIsSocialAccountExistUseCase(authRepository: AuthRepository): GetIsSocialAccountExistUseCase {
        return GetIsSocialAccountExistUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetSignUpPurposeListUseCase(authRepository: AuthRepository): GetSignUpPurposeListUseCase {
        return GetSignUpPurposeListUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun providePostAuthRegisterUseCase(authRepository: AuthRepository): PostAuthRegisterUseCase {
        return PostAuthRegisterUseCase(authRepository)
    }

}