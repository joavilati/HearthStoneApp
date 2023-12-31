package com.example.heartstone_repository.repository

import com.example.heartstone_repository.data.HearthStoneAPI
import com.example.heartstone_repository.model.CardResponse
import com.example.heartstone_repository.model.CardsByItemResponse
import com.example.heartstone_repository.model.InfoResponse
import com.example.heartstone_repository.utils.extensions.handleErrors

class HearthStoneDataRepository(
    private val api: HearthStoneAPI
) : HearthStoneRepository {

    override suspend fun getInfo(): InfoResponse {
        return api.getInfo().handleErrors()
    }

    override suspend fun getCardsBy(typeName: String, name: String): List<CardsByItemResponse> {
        return api.getCardsBy(typeName, name).handleErrors()
    }

    override suspend fun getSingleCard(name: String): List<CardResponse> {
        return api.getSingleCard( name).handleErrors()
    }

}