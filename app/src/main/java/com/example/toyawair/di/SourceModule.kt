package com.example.toyawair.di

import com.example.toyawair.data.source.remote.AwairRemoteDataSource
import com.example.toyawair.data.source.remote.AwairRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Singleton
    @Binds
    abstract fun bindAwairRemoteDataSource(awairRemoteDataSourceImpl: AwairRemoteDataSourceImpl): AwairRemoteDataSource

}