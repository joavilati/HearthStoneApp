package com.example.hearthstoneapp.di

import com.example.hearthstoneapp.domain.GetInfoUseCase
import com.example.heartstone_repository.data.HearthStoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetInfoUseCase(repo: HearthStoneRepository): GetInfoUseCase {
        return GetInfoUseCase(repo)
    }
}