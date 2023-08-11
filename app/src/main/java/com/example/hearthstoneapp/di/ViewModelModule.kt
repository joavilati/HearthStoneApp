package com.example.hearthstoneapp.di

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetInfoUseCase
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
    fun provideInfoViewModelFactory(
        infoState: MediatorLiveData<InfoViewModel.InfoState>,
        getInfoUseCase: GetInfoUseCase
    ): InfoViewModelFactory {
        return InfoViewModelFactory(infoState, getInfoUseCase)
    }
}