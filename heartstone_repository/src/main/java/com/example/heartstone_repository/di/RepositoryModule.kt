package com.example.heartstone_repository.di

import com.example.heartstone_repository.repository.HearthStoneDataRepository
import com.example.heartstone_repository.repository.HearthStoneRepository
import com.example.heartstone_repository.data.HearthStoneAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHearthStoneRepository(
        api: HearthStoneAPI
    ): HearthStoneRepository {
        return HearthStoneDataRepository(api)
    }
}