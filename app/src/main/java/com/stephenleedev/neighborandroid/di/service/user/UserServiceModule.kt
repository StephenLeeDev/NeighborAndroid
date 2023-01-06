package com.stephenleedev.neighborandroid.di.service.user

import com.stephenleedev.neighborandroid.BuildConfig
import com.stephenleedev.neighborandroid.remote.service.user.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2023/01/06.
 */

@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

    @Provides
    @Singleton
    fun provideUserService(okHttpClient: OkHttpClient): UserService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

}