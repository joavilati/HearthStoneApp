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
                val response = repo.getInfo()
                response.body()?.let { data ->
                    liveData.postValue(Result.OnSuccess(Info from data))
                    return@launch
                }
                throw GenericException()
            } catch (e: Exception) {
                liveData.postValue(Result.OnError(e.message ?: ""))
            }
        }
    }
}