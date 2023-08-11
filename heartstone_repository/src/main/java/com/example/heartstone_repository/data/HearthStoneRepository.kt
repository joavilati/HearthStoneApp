package com.example.heartstone_repository.data

import com.example.heartstone_repository.model.CardResponse
import com.example.heartstone_repository.model.CardsByItemResponse
import com.example.heartstone_repository.model.InfoResponse
import retrofit2.Response

interface HearthStoneRepository {
    suspend fun getInfo(): InfoResponse
    suspend fun getCardsBy(typeName: String, name: String): List<CardsByItemResponse>
    suspend fun getSingleCard(name: String): CardResponse
}