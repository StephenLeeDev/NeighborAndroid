package com.stephenleedev.neighborandroid.di

import com.stephenleedev.neighborandroid.BuildConfig
import com.stephenleedev.neighborandroid.domain.util.PrefUtil
import com.stephenleedev.neighborandroid.remote.util.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2022/12/18.
 */

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(prefUtil: PrefUtil): OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(false)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            addInterceptor(TokenInterceptor(prefUtil))
        }.build()
    }

}