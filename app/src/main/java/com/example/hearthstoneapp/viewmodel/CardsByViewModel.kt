package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.model.CardsBy

abstract class CardsByViewModel: ViewModel() {
    sealed class CardsByState {
        data class CardsByLoaded(val data: List<CardsBy>) : CardsByState()
        object ShowLoading : CardsByState()
        data class ShowError(val message: String) : CardsByState()
    }

    abstract fun getState(): LiveData<CardsByState>

    abstract fun clear()
    abstract fun getCardsBy(typeName: String, name: String)
}