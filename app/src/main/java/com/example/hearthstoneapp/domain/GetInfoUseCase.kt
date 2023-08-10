package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.Info
import com.example.heartstone_repository.data.HearthStoneRepository
import com.example.heartstone_repository.utils.GenericException
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
    private val repo: HearthStoneRepository
) : BaseUseCase<Result>() {
    override fun execute() {
        launch {
            try {
                val data = repo.getInfo()
                liveData.postValue(Result.OnSuccess(Info from data))

            } catch (e: Exception) {
                liveData.value = Result.OnError(e.message ?: "")
            }
        }
    }
}