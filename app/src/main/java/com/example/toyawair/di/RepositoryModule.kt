package com.example.toyawair.di

import com.example.toyawair.data.repo.AwairRepository
import com.example.toyawair.data.repo.AwairRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAwairRepository(awairRepositoryImpl: AwairRepositoryImpl): AwairRepository

}

