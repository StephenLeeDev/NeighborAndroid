package com.stephenleedev.neighborandroid.di.repository.auth

import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepository
import com.stephenleedev.neighborandroid.domain.repository.auth.AuthRepositoryImpl
import com.stephenleedev.neighborandroid.domain.util.PrefUtil
import com.stephenleedev.neighborandroid.remote.service.auth.AuthService
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
object AuthRepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        prefUtil: PrefUtil
    ) : AuthRepository {
        return AuthRepositoryImpl(authService, prefUtil)
    }

}