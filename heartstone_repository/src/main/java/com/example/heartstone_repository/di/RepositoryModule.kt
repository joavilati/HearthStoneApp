package com.example.heartstone_repository.di

import android.content.Context
import com.example.heartstone_repository.data.HearthStoneDataRepository
import com.example.heartstone_repository.data.HearthStoneRepository
import com.example.heartstone_repository.repository.HearthStoneAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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