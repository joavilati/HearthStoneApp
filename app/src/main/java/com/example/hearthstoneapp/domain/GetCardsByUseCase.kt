package com.example.hearthstoneapp.domain

import com.example.hearthstoneapp.model.CardsBy
import com.example.heartstone_repository.data.HearthStoneRepository
import javax.inject.Inject

class GetCardsByUseCase @Inject constructor(
    private val repo: HearthStoneRepository,
) : BaseUseCase<GetCardsByUseCase.Result>() {

    private var typeName = ""
    private var name = ""
    override fun execute() {
        launch {
            try {
                val data = repo.getCardsBy(typeName, name)
                liveData.postValue(Result.OnSuccess(CardsBy from data))

            } catch (e: Exception) {
                liveData.postValue(Result.OnError(e.message ?: ""))
            }
        }
    }

    fun setParams(typeName: String, name: String): GetCardsByUseCase {
        this.typeName = typeName
        this.name = name
        return this
    }

    sealed class Result {
        data class OnSuccess(val data: List<CardsBy>) : Result()
        class OnError(val message: String) : Result()
    }
}