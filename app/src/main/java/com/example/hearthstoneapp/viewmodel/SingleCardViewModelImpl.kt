package com.example.hearthstoneapp.viewmodel


import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetSingleCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleCardViewModelImpl @Inject constructor(
    private val state: MediatorLiveData<SingleCardState>,
    private val useCase: GetSingleCardUseCase
): SingleCardViewModel() {

    init {
        state.addSource(useCase.getLiveData(), ::onGetSingleCard)
    }

    override fun getState() = state

    override fun getSingleCard(name: String) {
        state.value = SingleCardState.ShowLoading
        useCase.setParams(name).execute()
    }

    override fun clear() {
        useCase.cleanUp()
    }

    fun onGetSingleCard(result: GetSingleCardUseCase.Result) {
        when(result){
            is GetSingleCardUseCase.Result.OnError -> {
                state.postValue(SingleCardState.ShowError(result.message))
            }
            is GetSingleCardUseCase.Result.OnSuccess -> {
                state.postValue(SingleCardState.SingleCardLoaded(result.data))
            }
        }
    }
}