package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.domain.GetInfoUseCase
import javax.inject.Inject

class CardsByViewModelFactory @Inject constructor(
    private val state: MediatorLiveData<CardsByViewModel.CardsByState>,
    private val getCardsByUseCase: GetCardsByUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardsByViewModelImpl::class.java)) {
            return CardsByViewModelImpl(state, getCardsByUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}