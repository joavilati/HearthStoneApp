package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hearthstoneapp.domain.GetInfoUseCase
import javax.inject.Inject

class InfoViewModelFactory @Inject constructor(
    private val infoState: MediatorLiveData<InfoViewModel.InfoState>,
    private val getInfoUseCase: GetInfoUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoViewModelImpl::class.java)) {
            return InfoViewModelImpl(infoState, getInfoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}