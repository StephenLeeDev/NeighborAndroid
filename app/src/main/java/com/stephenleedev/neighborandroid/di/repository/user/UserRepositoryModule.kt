package com.stephenleedev.neighborandroid.di.repository.user

import com.stephenleedev.neighborandroid.domain.repository.user.UserRepository
import com.stephenleedev.neighborandroid.domain.repository.user.UserRepositoryImpl
import com.stephenleedev.neighborandroid.remote.service.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService) : UserRepository {
        return UserRepositoryImpl(userService)
    }

}