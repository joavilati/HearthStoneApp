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

    private fun onGetInfo(result: GetInfoUseCase.Result) {
        when(result){
            is GetInfoUseCase.Result.OnError -> {
                infoState.value = InfoState.ShowError(result.message)
            }
            is GetInfoUseCase.Result.OnSuccess -> {
                infoState.value = InfoState.InfoLoaded(result.data)
                infoState.value = InfoState.ShowContent
            }
        }
    }
}