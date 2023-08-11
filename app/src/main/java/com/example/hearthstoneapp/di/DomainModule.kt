package com.example.hearthstoneapp.di

import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.domain.GetInfoUseCase
import com.example.hearthstoneapp.domain.GetSingleCardUseCase
import com.example.heartstone_repository.data.HearthStoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideGetInfoUseCase(repo: HearthStoneRepository): GetInfoUseCase {
        return GetInfoUseCase(repo)
    }

    @Provides
    fun provideGetCardsByUseCase(repo: HearthStoneRepository): GetCardsByUseCase {
        return GetCardsByUseCase(repo)
    }

    @Provides
    fun provideGetSingleCardUseCase(repo: HearthStoneRepository): GetSingleCardUseCase {
        return GetSingleCardUseCase(repo)
    }
}