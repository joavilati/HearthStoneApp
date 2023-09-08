package com.example.heartstone_repository.repository

import com.example.heartstone_repository.model.CardResponse
import com.example.heartstone_repository.model.CardsByItemResponse
import com.example.heartstone_repository.model.InfoResponse
import retrofit2.Response

interface HearthStoneRepository {
    suspend fun getInfo(): InfoResponse
    suspend fun getCardsBy(typeName: String, name: String): List<CardsByItemResponse>
    suspend fun getSingleCard(name: String): List<CardResponse>
}