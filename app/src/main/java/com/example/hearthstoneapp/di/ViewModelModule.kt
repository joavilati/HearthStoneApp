package com.example.hearthstoneapp.di

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.domain.GetInfoUseCase
import com.example.hearthstoneapp.domain.GetSingleCardUseCase
import com.example.hearthstoneapp.viewmodel.*
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
    fun provideSingleCardStateLiveData(): MediatorLiveData<SingleCardViewModel.SingleCardState> {
        return MediatorLiveData<SingleCardViewModel.SingleCardState>()
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

    @Provides
    fun provideCardsByViewModelFactory(
        state: MediatorLiveData<SingleCardViewModel.SingleCardState>,
        getCardsByUseCase: GetSingleCardUseCase
    ): SingleCardViewModelFactory {
        return SingleCardViewModelFactory(state, getCardsByUseCase)
    }
}