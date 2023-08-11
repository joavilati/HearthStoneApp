package com.example.hearthstoneapp.viewmodel


import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetCardsByUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardsByViewModelImpl @Inject constructor(
    private val state: MediatorLiveData<CardsByState>,
    private val getInfoUseCase: GetCardsByUseCase
): CardsByViewModel() {

    init {
        state.addSource(getInfoUseCase.getLiveData(), ::onGetCardsBy)
    }

    override fun getState() = state

    override fun getCardsBy(typeName: String, name: String) {
        state.value = CardsByState.ShowLoading
        getInfoUseCase.setParams(typeName, name).execute()
    }

    override fun clear() {
        getInfoUseCase.cleanUp()
    }

    fun onGetCardsBy(result: GetCardsByUseCase.Result) {
        when(result){
            is GetCardsByUseCase.Result.OnError -> {
                state.postValue(CardsByState.ShowError(result.message))
            }
            is GetCardsByUseCase.Result.OnSuccess -> {
                state.postValue(CardsByState.CardsByLoaded(result.data))
            }
        }
    }
}