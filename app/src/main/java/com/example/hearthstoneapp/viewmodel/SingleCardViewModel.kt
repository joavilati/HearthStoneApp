package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.model.Card
import com.example.hearthstoneapp.model.CardsBy

abstract class SingleCardViewModel: ViewModel() {
    sealed class SingleCardState {
        data class SingleCardLoaded(val data: Card) : SingleCardState()
        object ShowLoading : SingleCardState()
        data class ShowError(val message: String) : SingleCardState()
    }

    abstract fun getState(): LiveData<SingleCardState>

    abstract fun clear()

    abstract fun getSingleCard(name: String)
}