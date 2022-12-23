package com.stephenleedev.neighborandroid.di.service.auth

import com.stephenleedev.neighborandroid.BuildConfig
import com.stephenleedev.neighborandroid.remote.service.auth.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2022/12/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object AuthServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(okHttpClient: OkHttpClient): AuthService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

}