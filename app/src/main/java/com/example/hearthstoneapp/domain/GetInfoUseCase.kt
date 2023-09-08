package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.Info
import com.example.heartstone_repository.repository.HearthStoneRepository
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(
    private val repo: HearthStoneRepository,
) : BaseUseCase<GetInfoUseCase.Result>() {
    override fun execute() {
        launch {
            try {
                val data = repo.getInfo()
                liveData.postValue(Result.OnSuccess(Info from data))

            } catch (e: Exception) {
                liveData.postValue(Result.OnError(e.message ?: ""))
            }
        }
    }

    sealed class Result {
        data class OnSuccess(val data: Info) : Result()
        class OnError(val message: String) : Result()
    }
}