package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.model.Info

abstract class InfoViewModel: ViewModel() {

    sealed class State {
        data class InfoLoaded(val info: Info) : State()
        object ShowLoading : State()
        object ShowContent : State()
        object ShowError : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun getInfo()
}