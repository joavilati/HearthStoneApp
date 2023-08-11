package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.Card
import com.example.heartstone_repository.data.HearthStoneRepository
import javax.inject.Inject

class GetSingleCardUseCase @Inject constructor(
    private val repo: HearthStoneRepository,
) : BaseUseCase<GetSingleCardUseCase.Result>() {

    private var name = ""
    override fun execute() {
        launch {
            try {
                val data = repo.getSingleCard(name)
                liveData.postValue(Result.OnSuccess(Card from data))

            } catch (e: Exception) {
                liveData.postValue(Result.OnError(e.message ?: ""))
            }
        }
    }

    fun setParams( name: String): GetSingleCardUseCase {
        this.name = name
        return this
    }

    sealed class Result {
        data class OnSuccess(val data: Card) : Result()
        class OnError(val message: String) : Result()
    }
}