package com.example.toyawair.di

import com.example.toyawair.api.AwairApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_AWAIR_URL = "https://mobile-app-interview.awair.is/"

    @Singleton
    @Provides
    fun provideAwairApi(): AwairApi {
        return Retrofit.Builder()
            .baseUrl(BASE_AWAIR_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AwairApi::class.java)
    }
}