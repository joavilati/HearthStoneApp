package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import com.example.hearthstoneapp.domain.GetSingleCardUseCase
import javax.inject.Inject

class SingleCardViewModelFactory @Inject constructor(
    private val state: MediatorLiveData<SingleCardViewModel.SingleCardState>,
    private val useCase: GetSingleCardUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleCardViewModelImpl::class.java)) {
            return SingleCardViewModelImpl(state, useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}