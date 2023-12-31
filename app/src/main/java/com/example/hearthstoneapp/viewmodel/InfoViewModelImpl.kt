package com.example.hearthstoneapp.viewmodel


import androidx.lifecycle.MediatorLiveData
import com.example.hearthstoneapp.domain.GetInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val infoState: MediatorLiveData<InfoState>,
    private val getInfoUseCase: GetInfoUseCase
): InfoViewModel() {

    init {
        infoState.addSource(getInfoUseCase.getLiveData(), ::onGetInfo)
    }

    override fun getState() = infoState

    override fun getInfo() {
        infoState.value = InfoState.ShowLoading
        getInfoUseCase.execute()
    }

    override fun clear() {
        getInfoUseCase.cleanUp()
    }

    fun onGetInfo(result: GetInfoUseCase.Result) {
        when(result){
            is GetInfoUseCase.Result.OnError -> {
                infoState.postValue(InfoState.ShowError(result.message))
            }
            is GetInfoUseCase.Result.OnSuccess -> {
                infoState.postValue(InfoState.InfoLoaded(result.data))
            }
        }
    }
}