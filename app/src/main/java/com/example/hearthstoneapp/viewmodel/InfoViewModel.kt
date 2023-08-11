package com.example.hearthstoneapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hearthstoneapp.model.Info

abstract class InfoViewModel: ViewModel() {

    sealed class InfoState {
        data class InfoLoaded(val info: Info) : InfoState()
        object ShowLoading : InfoState()
        data class ShowError(val message: String) : InfoState()
    }

    abstract fun getState(): LiveData<InfoState>

    abstract fun getInfo()

    abstract fun clear()
}