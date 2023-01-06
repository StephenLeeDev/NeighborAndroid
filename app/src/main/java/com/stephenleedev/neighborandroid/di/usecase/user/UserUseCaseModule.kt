package com.stephenleedev.neighborandroid.di.usecase.user

import com.stephenleedev.neighborandroid.domain.repository.user.UserRepository
import com.stephenleedev.neighborandroid.domain.usecase.user.PatchUserThumbnailUseCase
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
object UserUseCaseModule {

    @Provides
    @Singleton
    fun providePatchUserThumbnailUseCase(userRepository: UserRepository): PatchUserThumbnailUseCase {
        return PatchUserThumbnailUseCase(userRepository)
    }

}