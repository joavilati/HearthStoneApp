package com.example.hearthstoneapp.di

import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.viewmodel.InfoViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class) // ou outro componente conforme necessário
object LiveDataModule {

    @Provides
    fun provideInfoStateLiveData(): MediatorLiveData<InfoViewModel.InfoState> {
        return MediatorLiveData<InfoViewModel.InfoState>()
    }
}