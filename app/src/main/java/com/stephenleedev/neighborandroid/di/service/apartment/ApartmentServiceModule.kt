package com.stephenleedev.neighborandroid.di.service.apartment

import com.stephenleedev.neighborandroid.BuildConfig
import com.stephenleedev.neighborandroid.remote.service.apartment.ApartmentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Written by StephenLeeDev on 2022/12/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApartmentServiceModule {

    @Provides
    @Singleton
    fun provideApartmentService(okHttpClient: OkHttpClient): ApartmentService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApartmentService::class.java)
    }

}