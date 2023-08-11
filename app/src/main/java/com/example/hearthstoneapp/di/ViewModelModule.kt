package com.example.hearthstoneapp.di

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.domain.GetInfoUseCase
import com.example.hearthstoneapp.viewmodel.CardsByViewModel
import com.example.hearthstoneapp.viewmodel.CardsByViewModelFactory
import com.example.hearthstoneapp.viewmodel.InfoViewModel
import com.example.hearthstoneapp.viewmodel.InfoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun provideInfoStateLiveData(): MediatorLiveData<InfoViewModel.InfoState> {
        return MediatorLiveData<InfoViewModel.InfoState>()
    }

    @Provides
    fun provideCardsByStateLiveData(): MediatorLiveData<CardsByViewModel.CardsByState> {
        return MediatorLiveData<CardsByViewModel.CardsByState>()
    }

    @Provides
    fun provideInfoViewModelFactory(
        infoState: MediatorLiveData<InfoViewModel.InfoState>,
        getInfoUseCase: GetInfoUseCase
    ): InfoViewModelFactory {
        return InfoViewModelFactory(infoState, getInfoUseCase)
    }

    @Provides
    fun provideCardsByViewModelFactory(
        state: MediatorLiveData<CardsByViewModel.CardsByState>,
        getCardsByUseCase: GetCardsByUseCase
    ): CardsByViewModelFactory {
        return CardsByViewModelFactory(state, getCardsByUseCase)
    }
}